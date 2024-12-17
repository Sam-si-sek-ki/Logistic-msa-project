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

    private String result;
    private String messageId;
    private String timestamp;

    public static SendSlackMessageResponse of(String result, String messageId, String timestamp) {
        return SendSlackMessageResponse.builder()
                .result(result)
                .messageId(messageId)
                .timestamp(timestamp)
                .build();
    }
}
