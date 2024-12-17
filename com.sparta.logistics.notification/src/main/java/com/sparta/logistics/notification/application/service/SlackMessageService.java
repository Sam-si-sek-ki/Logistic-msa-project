package com.sparta.logistics.notification.application.service;

import com.sparta.logistics.notification.domain.model.SlackMessage;
import com.sparta.logistics.notification.domain.model.SlackMessageStatus;
import com.sparta.logistics.notification.domain.repository.SlackMessageRepository;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageRequest;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlackMessageService {

    private final SlackMessageRepository slackMessageRepository;

    @Transactional
    public SendSlackMessageResponse saveSlackMessage(SendSlackMessageRequest request) {
        // SlackMessage 엔티티 생성
        SlackMessage slackMessage = SlackMessage.create(
                request.getRecipientId(),
                request.getMessage(),
                SlackMessageStatus.PENDING,
                request.getServiceName(),
                request.getReferenceId()
        );

        slackMessageRepository.save(slackMessage);

        return SendSlackMessageResponse.of("result", "messageId", "timestamp");
    }
}