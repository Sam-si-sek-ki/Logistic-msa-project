package com.sparta.logistics.hub.application.service;

import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_NOT_FOUND;
import static com.sparta.logistics.hub.domain.model.QHub.hub;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.HubPageResponse;
import com.sparta.logistics.hub.application.dto.HubRequestDto;
import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.domain.mapper.HubMapper;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import com.sparta.logistics.hub.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;
    private final HubMapper hubMapper;

    @Transactional(readOnly = true)
    public HubResponseDto getHubById(String hubId) {
        return hubMapper.toDto(
            hubRepository.findById(hubId).orElseThrow( () -> new GlobalException(HUB_NOT_FOUND))
        );
    }

    @Transactional(readOnly = true)
    public HubPageResponse getHubs(Predicate predicate, Pageable pageable) {

        BooleanBuilder bb = new BooleanBuilder(predicate);
        bb.and(hub.isDeleted.eq(false));

        Page<Hub> hubPage = hubRepository.findAll(bb, pageable);
        return HubPageResponse.of(hubPage);
    }

    public void insertHub(HubRequestDto request) {

        Hub hub = hubMapper.toEntity(request);
        hubRepository.save(hub);
    }

    public void deleteHub(String hubId) {

        Hub hub = hubRepository.findById(hubId).orElseThrow( () -> new GlobalException(HUB_NOT_FOUND));

        hub.setDelete(
                LocalDateTime.now(),
                "username"
        );
    }

    @Transactional
    public void updateHub(String hubId, HubRequestDto request) {

        Hub hub = hubRepository.findById(hubId).orElseThrow( () -> new GlobalException(HUB_NOT_FOUND));

        hub.update(
                request.name(),
                request.address(),
                request.addressDetail(),
                request.latitude() != null ? request.latitude() : hub.getLatitude(),
                request.longitude() != null ? request.longitude() : hub.getLongitude()
        );
    }
}
