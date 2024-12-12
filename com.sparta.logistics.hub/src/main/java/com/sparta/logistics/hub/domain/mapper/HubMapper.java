package com.sparta.logistics.hub.domain.mapper;

import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.domain.model.Hub;
import org.springframework.stereotype.Component;

@Component
public class HubMapper {
    // Entity -> DTO 변환
    public HubResponseDto toDto(Hub hub) {
        return new HubResponseDto(
            hub.getHubId(),
            hub.getName(),
            hub.getAddress(),
            hub.getAddressDetail(),
            hub.getLatitude(),
            hub.getLongitude()
        );
    }

    // DTO -> Entity 변환 (필요 시)
    public Hub toEntity(HubResponseDto dto) {
        return Hub.builder()
            .hubId(dto.hubId())
            .name(dto.name())
            .address(dto.address())
            .addressDetail(dto.addressDetail())
            .latitude(dto.latitude())
            .longitude(dto.longitude())
            .build();
    }
}
