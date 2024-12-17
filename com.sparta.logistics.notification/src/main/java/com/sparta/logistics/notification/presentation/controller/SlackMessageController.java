package com.sparta.logistics.notification.presentation.controller;

import com.sparta.logistics.notification.application.service.SlackMessageService;
import com.sparta.logistics.notification.libs.model.ResponseMessage;
import com.sparta.logistics.notification.libs.model.SuccessResponse;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageRequest;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class SlackMessageController {

    private final SlackMessageService slackMessageService;

    @PostMapping("/slack/send")
    @Operation(summary = "슬랙 메시지 전송", description = "슬랙 메시지 전송 요청 API")
    @Parameters({
            @Parameter(name = "recipientId", description = "슬랙 수신자 ID", example = "U085GB6CSRJ", required = true),
            @Parameter(name = "message", description = "보낼 메시지 내용", example = "This is a test Slack message.", required = true),
            @Parameter(name = "serviceName", description = "요청 서비스명", example = "DELIVERY"),
            @Parameter(name = "referenceId", description = "요청 서비스의 참조 엔티티ID", example = "b8b06bde-3e4d-4e16-9c13-2d23e92bb63d")
    })
    public ResponseEntity<SuccessResponse<SendSlackMessageResponse>> sendSlackMessage(
            @RequestBody @Valid SendSlackMessageRequest request) {
        return ResponseEntity.ok().body(
                SuccessResponse.of(ResponseMessage.SLACK_MESSAGE_SEND_SUCCESS, slackMessageService.sendSlackMessage(request)));
    }
}

