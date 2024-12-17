package com.sparta.logistics.hub.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.*;
import com.sparta.logistics.hub.domain.mapper.HubConnectionInfoMapper;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubConnectionInfo;
import com.sparta.logistics.hub.domain.repository.HubConnectionInfoRepository;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import com.sparta.logistics.hub.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.sparta.logistics.hub.domain.model.QHubConnectionInfo.hubConnectionInfo;
import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_CONNECTION_INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HubConnectionInfoService {

    private final HubConnectionInfoRepository hubConnectionInfoRepository;
    private final HubRepository hubRepository;
    private final HubConnectionInfoMapper hubConnectionInfoMapper;

    @Transactional(readOnly = true)
    public HubConnectionInfoResponseDto getHubConnectionInfosById(UUID id) {
        return hubConnectionInfoMapper.toDto(
                hubConnectionInfoRepository.findById(id).orElseThrow( () -> new GlobalException(HUB_CONNECTION_INFO_NOT_FOUND))
        );
    }

    @Transactional(readOnly = true)
    public HubConnectionInfoPageResponse getHubConnectionInfo(Predicate predicate, Pageable pageable) {
        BooleanBuilder bb = new BooleanBuilder(predicate);
        bb.and(hubConnectionInfo.isDeleted.eq(false));

        Page<HubConnectionInfo> hubPage = hubConnectionInfoRepository.findAll(bb, pageable);

        return HubConnectionInfoPageResponse.of(hubPage);
    }

    @Transactional
    public void createHubConnectionInfo(HubConnectionInfoRequestDto request) {

        Hub fromHub = hubRepository.findById(UUID.fromString(request.fromHubId())).orElseThrow(() -> new GlobalException(HUB_CONNECTION_INFO_NOT_FOUND));
        Hub toHub = hubRepository.findById(UUID.fromString(request.toHubId())).orElseThrow(() -> new GlobalException(HUB_CONNECTION_INFO_NOT_FOUND));

        HubConnectionInfo hubConnectionInfo = hubConnectionInfoMapper.toEntity(fromHub, toHub);
        hubConnectionInfoRepository.save(hubConnectionInfo);
    }

    @Transactional
    public void deleteHubConnectionInfo(UUID id) {

        HubConnectionInfo hubConnectionInfo = hubConnectionInfoRepository.findById(id).orElseThrow(() -> new GlobalException(HUB_CONNECTION_INFO_NOT_FOUND));

        hubConnectionInfo.setDelete(LocalDateTime.now(), "username");
    }
}
