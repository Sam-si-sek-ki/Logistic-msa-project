package com.sparta.logistics.hub.presentation.controller;

import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.HubPageResponse;
import com.sparta.logistics.hub.application.dto.HubRequestDto;
import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.application.service.HubService;
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

import static com.sparta.logistics.hub.libs.model.ResponseMessage.HUB_CREATE_SUCCESS;
import static com.sparta.logistics.hub.libs.model.ResponseMessage.HUB_SELECT_SUCCESS;

@RestController
@RequestMapping("/hub")
@RequiredArgsConstructor
@Slf4j
public class HubController {

    private final HubService hubService;

    @GetMapping("/{hubId}")
    public ResponseEntity<SuccessResponse<HubResponseDto>> getHubById(@PathVariable("hubId") String hubId){
        log.info("👀👀 get hub by id: {} 👀👀", hubId);
        return ResponseEntity.ok(SuccessResponse.of(HUB_SELECT_SUCCESS,hubService.getHubById(UUID.fromString(hubId))));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<HubPageResponse>> getHubs(
            @QuerydslPredicate(root = Hub.class) Predicate predicate,
            @PageableDefault(sort = "hubId", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(SuccessResponse.of(HUB_SELECT_SUCCESS,hubService.getHubs(predicate, pageable)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<HubResponseDto>> createHub(@RequestBody HubRequestDto request){
        hubService.insertHub(request);
        return ResponseEntity.ok(SuccessResponse.of(HUB_CREATE_SUCCESS));
    }

    @DeleteMapping("/{hubId}")
    public ResponseEntity<SuccessResponse<HubResponseDto>> deleteHub(@PathVariable("hubId") String hubId){
        hubService.deleteHub(UUID.fromString(hubId));
        return ResponseEntity.ok(SuccessResponse.of(HUB_CREATE_SUCCESS));
    }

    @PutMapping("/{hubId}")
    public ResponseEntity<SuccessResponse<HubResponseDto>> deleteHub(
            @PathVariable("hubId") String hubId,
            @RequestBody HubRequestDto request
    ){
        hubService.updateHub(UUID.fromString(hubId), request);
        return ResponseEntity.ok(SuccessResponse.of(HUB_CREATE_SUCCESS));
    }
}
