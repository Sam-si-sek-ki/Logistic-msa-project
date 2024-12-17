package com.sparta.logistics.notification.libs.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.logistics.notification.libs.model.ResponseMessage;
import lombok.Builder;

@Builder
public record SuccessResponse<T>(
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL) T data
) {

    public static <T> SuccessResponse<T> of(ResponseMessage message, T data) {
        return SuccessResponse.<T>builder()
                .code("SUCCESS")
                .message(message.getMessage())
                .data(data)
                .build();
    }

    public static <T> SuccessResponse<T> of(ResponseMessage message) {
        return SuccessResponse.<T>builder()
                .code("SUCCESS")
                .message(message.getMessage())
                .build();
    }

    public static <T> SuccessResponse<T> of(T data) {
        return SuccessResponse.<T>builder()
                .code("SUCCESS")
                .message("")
                .data(data)
                .build();
    }

    public static <T> SuccessResponse<T> of(String code, ResponseMessage message) {
        return SuccessResponse.<T>builder()
                .code(code)
                .message(message.getMessage())
                .build();
    }
}