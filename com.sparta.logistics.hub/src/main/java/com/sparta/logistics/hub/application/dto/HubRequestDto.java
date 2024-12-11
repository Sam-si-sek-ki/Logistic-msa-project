package com.sparta.logistics.hub.application.dto;

public record HubRequestDto(
        String name,
        String address,
        String addressDetail,
        double latitude,
        double longitude
) {
}
