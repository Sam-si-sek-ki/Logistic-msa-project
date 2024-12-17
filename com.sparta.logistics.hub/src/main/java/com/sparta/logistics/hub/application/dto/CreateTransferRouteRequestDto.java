package com.sparta.logistics.hub.application.dto;

public record CreateTransferRouteRequestDto(
        String fromHubId,
        String toHubId
) {
}
