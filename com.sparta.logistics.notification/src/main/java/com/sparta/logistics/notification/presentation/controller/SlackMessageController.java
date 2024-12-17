package com.sparta.logistics.notification.presentation.controller;

import com.sparta.logistics.notification.application.service.SlackMessageService;
import com.sparta.logistics.notification.libs.model.ResponseMessage;
import com.sparta.logistics.notification.libs.model.SuccessResponse;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageRequest;
import com.sparta.logistics.notification.presentation.dto.SendSlackMessageResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<SuccessResponse<SendSlackMessageResponse>> sendSlackMessage(
            @RequestBody @Valid SendSlackMessageRequest request) {
        return ResponseEntity.ok().body(
                SuccessResponse.of(ResponseMessage.SLACK_MESSAGE_SEND_SUCCESS, slackMessageService.sendSlackMessage(request)));
    }
}

