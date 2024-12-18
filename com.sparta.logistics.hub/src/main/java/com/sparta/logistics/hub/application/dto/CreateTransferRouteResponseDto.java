package com.sparta.logistics.hub.application.dto;

public record CreateTransferRouteResponseDto(
        int sequence,
        String fromHubId,
        String toHubId,
        long expectedDistance,
        long expectedDuration
) {
}
