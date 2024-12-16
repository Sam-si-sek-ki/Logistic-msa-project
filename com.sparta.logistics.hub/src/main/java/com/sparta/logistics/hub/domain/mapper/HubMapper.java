package com.sparta.logistics.hub.domain.mapper;

import com.sparta.logistics.hub.application.dto.HubRequestDto;
import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.domain.model.Hub;
import org.springframework.stereotype.Component;

@Component
public class HubMapper {
    // Entity -> DTO 변환
    public HubResponseDto toDto(Hub hub) {
        return new HubResponseDto(
            hub.getHubId().toString(),
            hub.getName(),
            hub.getAddress(),
            hub.getAddressDetail(),
            hub.getLatitude(),
            hub.getLongitude()
        );
    }

    // DTO -> Entity 변환 (필요 시)
    public Hub toEntity(HubRequestDto dto) {
        return Hub.builder()
            .name(dto.name())
            .address(dto.address())
            .addressDetail(dto.addressDetail())
            .latitude(dto.latitude())
            .longitude(dto.longitude())
            .build();
    }
}
