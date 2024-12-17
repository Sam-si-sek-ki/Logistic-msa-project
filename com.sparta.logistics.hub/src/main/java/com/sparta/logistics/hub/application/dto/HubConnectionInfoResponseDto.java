package com.sparta.logistics.hub.application.dto;

public record HubConnectionInfoResponseDto(
        String hubConnectionId,
        String fromHubId,
        String toHubId
) {
}
