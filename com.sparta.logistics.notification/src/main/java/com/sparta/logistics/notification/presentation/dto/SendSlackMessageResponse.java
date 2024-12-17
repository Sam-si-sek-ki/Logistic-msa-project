package com.sparta.logistics.notification.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendSlackMessageResponse {

    private String slackMessageId;

    public static SendSlackMessageResponse of(String slackMessageId) {
        return SendSlackMessageResponse.builder()
                .slackMessageId(slackMessageId)
                .build();
    }
}
