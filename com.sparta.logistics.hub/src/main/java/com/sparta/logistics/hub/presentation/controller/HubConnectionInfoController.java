package com.sparta.logistics.hub.presentation.controller;

import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.*;
import com.sparta.logistics.hub.application.service.HubConnectionInfoService;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.libs.model.ResponseMessage;
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
@RequestMapping("/hub-connection-infos")
@RequiredArgsConstructor
@Slf4j
public class HubConnectionInfoController {

    private final HubConnectionInfoService hubConnectionInfoService;

    @GetMapping("/{hubConnectionInfoId}")
    public ResponseEntity<SuccessResponse<HubConnectionInfoResponseDto>> getHubConnectionInfoById(@PathVariable("hubConnectionInfoId") String id){
        return ResponseEntity.ok(SuccessResponse.of(HUB_CONNECTION_INFO_SELECT_SUCCESS, hubConnectionInfoService.getHubConnectionInfosById(UUID.fromString(id))));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<HubConnectionInfoPageResponse>> getConnectionInfos(
            @QuerydslPredicate(root = Hub.class) Predicate predicate,
            @PageableDefault(sort = "hubConnectionInfoId", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(SuccessResponse.of(HUB_CONNECTION_INFO_SELECT_SUCCESS,hubConnectionInfoService.getHubConnectionInfo(predicate, pageable)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<HubConnectionInfoResponseDto>> createHubConnectionInfo(@RequestBody HubConnectionInfoRequestDto request){
        hubConnectionInfoService.createHubConnectionInfo(request);
        return ResponseEntity.ok(SuccessResponse.of(HUB_CONNECTION_INFO_CREATE_SUCCESS));
    }

    @DeleteMapping("/{hubConnectionInfoId}")
    public ResponseEntity<SuccessResponse<HubConnectionInfoResponseDto>> deleteConnectionInfo(@PathVariable("hubConnectionInfoId") String hubId){
        hubConnectionInfoService.deleteHubConnectionInfo(UUID.fromString(hubId));
        return ResponseEntity.ok(SuccessResponse.of(HUB_CONNECTION_INFO_DELETE_SUCCESS));
    }
}
