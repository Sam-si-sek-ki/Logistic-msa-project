package com.sparta.logistics.hub.presentation.controller;

import com.sparta.logistics.hub.application.dto.HubResponseDto;
import com.sparta.logistics.hub.application.service.HubService;
import com.sparta.logistics.hub.libs.model.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hub")
@RequiredArgsConstructor
@Slf4j
public class HubController {

    private final HubService hubService;

    @GetMapping("/{hubId}")
    public ResponseEntity<SuccessResponse<HubResponseDto>> getHubById(@PathVariable("hubId") String hubId){
        return ResponseEntity.ok(SuccessResponse.of(hubService.getHubById(hubId)));
    }
}
