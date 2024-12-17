package com.sparta.logistics.hub.presentation.controller;

import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.*;
import com.sparta.logistics.hub.application.service.HubTransferService;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.libs.model.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.sparta.logistics.hub.libs.model.ResponseMessage.*;

@RestController
@RequestMapping("/hub-transfers")
@RequiredArgsConstructor
@Slf4j
public class HubTransferController {

    private final HubTransferService hubTransferService;

    @GetMapping("/{hubTransferId}")
    public ResponseEntity<SuccessResponse<HubTransferResponseDto>> getHubTransfersById(@PathVariable("hubTransferId") String id){
        return ResponseEntity.ok(SuccessResponse.of(HUB_TRANSFER_SELECT_SUCCESS,hubTransferService.getHubTransfersById(UUID.fromString(id))));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<HubTransferPageResponse>> getHubTransfers(
            @QuerydslPredicate(root = Hub.class) Predicate predicate,
            @PageableDefault(sort = "hubTransferId", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(SuccessResponse.of(HUB_TRANSFER_SELECT_SUCCESS,hubTransferService.getHubTransfers(predicate, pageable)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<HubTransferResponseDto>> createHubTransfer(@RequestBody HubTransferRequestDto request){
        hubTransferService.createHubTransfer(request);
        return ResponseEntity.ok(SuccessResponse.of(HUB_TRANSFER_CREATE_SUCCESS));
    }

    @DeleteMapping("/{hubTransferId}")
    public ResponseEntity<SuccessResponse<HubTransferResponseDto>> deleteHubTransfer(@PathVariable("hubTransferId") String hubId){
        hubTransferService.deleteHubTransfer(UUID.fromString(hubId));
        return ResponseEntity.ok(SuccessResponse.of(HUB_TRANSFER_DELETE_SUCCESS));
    }
}
