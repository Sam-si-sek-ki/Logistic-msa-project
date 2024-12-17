package com.sparta.logistics.notification.domain.model;

import lombok.Getter;

@Getter
public enum SlackReservationStatus {
    SCHEDULED("전송 예약"),
    SUCCESS("전송 완료"),
    FAILED("전송 실패");


    private final String description;

    SlackReservationStatus(String description) {
        this.description = description;
    }
}
