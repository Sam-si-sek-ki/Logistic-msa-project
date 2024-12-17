package com.sparta.logistics.hub.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.*;
import com.sparta.logistics.hub.domain.mapper.HubTransferMapper;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubTransfer;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import com.sparta.logistics.hub.domain.repository.HubTransferRepository;
import com.sparta.logistics.hub.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.sparta.logistics.hub.domain.model.QHubTransfer.hubTransfer;
import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_TRANSFER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class HubTransferService {

    private final HubTransferRepository hubTransferRepository;
    private final HubRepository hubRepository;
    private final HubTransferMapper hubTransferMapper;

    @Transactional(readOnly = true)
    public HubTransferResponseDto getHubTransfersById(UUID id) {
        return hubTransferMapper.toDto(
                hubTransferRepository.findById(id).orElseThrow( () -> new GlobalException(HUB_TRANSFER_NOT_FOUND))
        );
    }

    @Transactional(readOnly = true)
    public HubTransferPageResponse getHubTransfers(Predicate predicate, Pageable pageable) {
        BooleanBuilder bb = new BooleanBuilder(predicate);
        bb.and(hubTransfer.isDeleted.eq(false));

        Page<HubTransfer> hubPage = hubTransferRepository.findAll(bb, pageable);
        return HubTransferPageResponse.of(hubPage);
    }

    @Transactional
    public void createHubTransfer(HubTransferRequestDto request) {

        Hub fromHub = hubRepository.findById(UUID.fromString(request.fromHubId())).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));
        Hub toHub = hubRepository.findById(UUID.fromString(request.toHubId())).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));

        HubTransfer hubTransfer = hubTransferMapper.toEntity(request, fromHub, toHub);
        hubTransferRepository.save(hubTransfer);
    }

    @Transactional
    public void deleteHubTransfer(UUID id) {

        HubTransfer hubTransfer = hubTransferRepository.findById(id).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));

        hubTransfer.setDelete(LocalDateTime.now(), "username");
    }
}
