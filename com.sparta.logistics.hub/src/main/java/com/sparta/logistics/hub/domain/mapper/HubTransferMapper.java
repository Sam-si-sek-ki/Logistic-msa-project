package com.sparta.logistics.hub.domain.mapper;

import com.sparta.logistics.hub.application.dto.HubTransferRequestDto;
import com.sparta.logistics.hub.application.dto.HubTransferResponseDto;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubTransfer;
import org.springframework.stereotype.Component;

@Component
public class HubTransferMapper {

    // Entity -> DTO 변환
    public static HubTransferResponseDto toDto(HubTransfer hubTransfer) {
        return new HubTransferResponseDto(
                hubTransfer.getHubTransferId().toString(),
                hubTransfer.getFromHub().getHubId().toString(),
                hubTransfer.getToHub().getHubId().toString(),
                hubTransfer.getDuration(),
                hubTransfer.getDistance()
        );
    }

    // DTO -> Entity 변환 (필요 시)
    public HubTransfer toEntity(HubTransferRequestDto dto, Hub fromHub, Hub toHub) {
        return HubTransfer.builder()
                .fromHub(fromHub)
                .toHub(toHub)
                .duration(dto.duration())
                .distance(dto.distance())
                .build();
    }
}
