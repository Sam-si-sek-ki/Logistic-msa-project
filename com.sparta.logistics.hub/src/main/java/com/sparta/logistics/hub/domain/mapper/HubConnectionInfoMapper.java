package com.sparta.logistics.hub.domain.mapper;

import com.sparta.logistics.hub.application.dto.HubConnectionInfoResponseDto;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubConnectionInfo;
import org.springframework.stereotype.Component;

@Component
public class HubConnectionInfoMapper {
    // Entity -> DTO 변환
    public HubConnectionInfoResponseDto toDto(HubConnectionInfo hubConnectionInfo) {
        return new HubConnectionInfoResponseDto(
                hubConnectionInfo.getHubConnectionInfoId().toString(),
                hubConnectionInfo.getFromHub().getHubId().toString(),
                hubConnectionInfo.getToHub().getHubId().toString()
        );
    }

    // DTO -> Entity 변환 (필요 시)
    public HubConnectionInfo toEntity(Hub fromHub, Hub toHub) {
        return HubConnectionInfo.builder()
                .fromHub(fromHub)
                .toHub(toHub)
                .build();
    }
}