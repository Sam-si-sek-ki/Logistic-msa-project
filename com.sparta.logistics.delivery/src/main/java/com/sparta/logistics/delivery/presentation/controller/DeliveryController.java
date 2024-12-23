package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryResponse;
import com.sparta.logistics.delivery.application.dto.delivery.GetDeliveryResponse;
import com.sparta.logistics.delivery.application.dto.delivery.UpdateDeliveryRequest;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.client.order.OrderResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/deliveries")
@RequiredArgsConstructor
@RestController
public class DeliveryController {

  private final DeliveryService deliveryService;

  @PostMapping
  public ResponseEntity<CreateDeliveryResponse> createDelivery(
      @RequestBody OrderResponseDto orderResponseDto,
      @RequestHeader("X-Role") String role,
      @RequestHeader("X-Username") String username) {
    CreateDeliveryResponse response = deliveryService.createDelivery(orderResponseDto,username,role);
    return ResponseEntity.ok(response);
  }

//  @PostMapping("/test")
//  public ResponseEntity<CreateDeliveryResponse> createDeliveryTest(
//      @RequestBody OrderResponseDto orderResponseDto
//  ) {
//    return ResponseEntity.ok(deliveryService.createDelivery(orderResponseDto));
//  }


  @PutMapping("/{deliveryId}")
  public ResponseEntity<GetDeliveryResponse> updateDelivery(
      @PathVariable UUID deliveryId,
      @RequestBody @Valid UpdateDeliveryRequest request
  ) {
    return ResponseEntity.ok(deliveryService.updateDelivery(deliveryId, request));
  }

  @DeleteMapping("/{deliveryId}")
  public ResponseEntity<Void> deleteDelivery(
      @PathVariable UUID deliveryId,
      @RequestHeader("X-USER-ID") Long userId,
      @RequestHeader("X-USER-ROLE") String userRole
  ) {
    deliveryService.deleteDelivery(deliveryId, userId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{deliveryId}")
  public ResponseEntity<GetDeliveryResponse> getDelivery(@PathVariable UUID deliveryId) {
    return ResponseEntity.ok(deliveryService.getDelivery(deliveryId));
  }

  @GetMapping
  public ResponseEntity<List<GetDeliveryResponse>> searchDeliveries(
      @RequestParam(required = false) DeliveryStatus status  
  ) {
    return ResponseEntity.ok(deliveryService.findDeliveries(status));
  }


}
