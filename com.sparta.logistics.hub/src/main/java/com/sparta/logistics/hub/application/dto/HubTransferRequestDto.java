package com.sparta.logistics.hub.application.dto;

public record HubTransferRequestDto(
    String fromHubId,
    String toHubId,
    int duration,
    int distance
) {
}
