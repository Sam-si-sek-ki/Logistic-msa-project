package com.sparta.logistics.notification.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.logistics.notification.domain.model.SlackMessage;
import com.sparta.logistics.notification.domain.model.SlackMessageStatus;
import com.sparta.logistics.notification.domain.repository.SlackMessageRepository;
import com.sparta.logistics.notification.libs.exception.ErrorCode;
import com.sparta.logistics.notification.libs.exception.GlobalException;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageRequest;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackMessageService {

    private final SlackMessageRepository slackMessageRepository;
    private final RestTemplate restTemplate;

    @Value("${slack.api.url}") // Slack API URL
    private String slackApiUrl;

    @Value("${slack.bot.token}") // Slack Bot Token
    private String slackBotToken;

    private static final int MAX_RETRY_COUNT = 3; // 최대 재시도 횟수
    private static final long RETRY_DELAY_MS = 2000; // 재시도 간격 (2초)

    // 슬랙 메시지 저장 및 전송
    @Transactional
    public SendSlackMessageResponse sendSlackMessage(SendSlackMessageRequest request) {
        SlackMessage slackMessage = SlackMessage.create(
                request.getRecipientId(),
                request.getMessage(),
                SlackMessageStatus.PENDING,
                request.getServiceName(),
                request.getReferenceId()
        );
        slackMessageRepository.save(slackMessage);

        // Slack API 호출 및 Retry 로직 실행
        Map<String, Object> responseBody = sendSlackMessageWithRetry(slackMessage, request.getRecipientId(), request.getMessage());

        if (responseBody != null && Boolean.TRUE.equals(responseBody.get("ok"))) {
            // 응답에서 message.text와 ts 값을 가져와 업데이트
            Map<String, Object> messageMap = (Map<String, Object>) responseBody.get("message");
            String slackTimestamp = responseBody.get("ts") != null ? responseBody.get("ts").toString() : null;

            slackMessage.update(
                    null, null, SlackMessageStatus.SUCCESS,
                    null, null, null, slackTimestamp
            );
        } else {
            slackMessage.update(null, null, SlackMessageStatus.FAILED, null, null, null, null);
            throw new GlobalException(ErrorCode.SLACK_MESSAGE_SEND_FAILED);
        }

        slackMessageRepository.save(slackMessage);

        return SendSlackMessageResponse.of(slackMessage.getSlackMessageId().toString());
    }

    // Retry 로직을 포함한 Slack 메시지 전송 메서드
    private Map<String, Object> sendSlackMessageWithRetry(SlackMessage slackMessage, String channel, String message) {
        int retryCount = 0;

        while (retryCount < MAX_RETRY_COUNT) {
            try {
                String response = sendSlackMessageToAPI(channel, message);
                Map<String, Object> responseBody = parseResponse(response);

                // Slack API 응답 성공 여부 확인
                if (Boolean.TRUE.equals(responseBody.get("ok"))) {
                    log.info("Slack message sent successfully.");
                    return responseBody; // 성공 시 응답 반환
                } else {
                    log.error("Slack message failed: {}", responseBody);
                    retryCount++;
                    updateRetryCount(slackMessage, retryCount);
                }
            } catch (Exception e) {
                log.error("Error sending Slack message (attempt {}): {}", retryCount + 1, e.getMessage());
                retryCount++;
                updateRetryCount(slackMessage, retryCount);
            }

            // 재시도 딜레이 설정
            if (retryCount < MAX_RETRY_COUNT) {
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Retry sleep interrupted", e);
                    return null; // 인터럽트 발생 시 중단
                }
            }
        }

        log.error("Failed to send Slack message after {} attempts.", MAX_RETRY_COUNT);
        return null; // 최대 재시도 횟수 초과 시 실패
    }


    // Slack 메시지 전송 API
    private String sendSlackMessageToAPI(String channel, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(slackBotToken);

        Map<String, String> body = new HashMap<>();
        body.put("channel", channel);
        body.put("text", message);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        String url = slackApiUrl + "/chat.postMessage";

        log.info("Sending Slack message to channel: {}", channel);
        return restTemplate.postForObject(url, request, String.class);
    }

    // Email 주소로 Slack User ID 가져오기
    private String getUserIdByEmail(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(slackBotToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String url = slackApiUrl + "/users.lookupByEmail?email=" + email;

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, Map.class
        );

        Map responseBody = responseEntity.getBody();
        if (responseBody != null && Boolean.TRUE.equals(responseBody.get("ok"))) {
            Map<String, Object> user = (Map<String, Object>) responseBody.get("user");
            return (String) user.get("id");
        } else {
            log.error("Failed to find Slack User ID: {}", responseBody);
            throw new RuntimeException("Failed to find Slack User ID for email: " + email);
        }
    }

    // Slack API 응답 파싱 메서드
    private Map<String, Object> parseResponse(String response) {
        try {
            // ObjectMapper를 사용하여 JSON 문자열을 Map으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, Map.class);
        } catch (Exception e) {
            log.error("Failed to parse Slack response: {}", e.getMessage());
            throw new RuntimeException("Failed to parse Slack response.");
        }
    }

    // retry 증가
    private void updateRetryCount(SlackMessage slackMessage, int retryCount) {
        slackMessage.updateRetryCount(retryCount);
        slackMessageRepository.save(slackMessage);
    }
}