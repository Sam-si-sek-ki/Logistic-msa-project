package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryResponse;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import com.sparta.logistics.delivery.infrastructure.client.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliveryController {

  private final DeliveryService deliveryService;

  @PostMapping
  public ResponseEntity<CreateDeliveryResponse> createDelivery(@RequestBody OrderResponseDto orderResponseDto) {
    CreateDeliveryResponse response = deliveryService.createDelivery(orderResponseDto);
    return ResponseEntity.ok(response);
  }
}
