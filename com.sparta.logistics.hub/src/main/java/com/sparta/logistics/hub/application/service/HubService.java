package com.sparta.logistics.hub.application.service;

import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_NOT_FOUND;

import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.domain.mapper.HubMapper;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import com.sparta.logistics.hub.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;
    private final HubMapper hubMapper;

    public HubResponseDto getHubById(String hubId) {
        return hubMapper.toDto(
            hubRepository.findById(hubId).orElseThrow( () -> new GlobalException(HUB_NOT_FOUND))
        );
    }
}
