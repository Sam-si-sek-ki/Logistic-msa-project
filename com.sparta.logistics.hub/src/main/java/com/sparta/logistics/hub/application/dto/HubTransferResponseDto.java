package com.sparta.logistics.hub.application.dto;

public record HubTransferResponseDto(
        String hubTransferId,
        String fromHubId,
        String toHubId,
        int duration,
        int distance
) {
}
