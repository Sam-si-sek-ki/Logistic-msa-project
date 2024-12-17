package com.sparta.logistics.hub.application.dto;

public record HubConnectionInfoRequestDto(
        String fromHubId,
        String toHubId
) {
}
