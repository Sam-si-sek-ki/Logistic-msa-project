package com.sparta.logistics.order.presentation.controller;

import com.sparta.logistics.order.application.service.OrderService;
import com.sparta.logistics.order.libs.model.ResponseMessage;
import com.sparta.logistics.order.libs.model.SuccessResponse;
import com.sparta.logistics.order.presentation.dto.OrderRequestDto;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // todo : 권한 추가
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createOrder(
        @RequestBody @Valid OrderRequestDto request) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.ORDER_CREATE_SUCCESS,
                orderService.createOrder(request)));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SuccessResponse<?>> getOrders(@PathVariable UUID orderId) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.ORDER_GET_SUCCESS,
                orderService.getOrder(orderId)));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SuccessResponse<?>> updateOrder(@PathVariable UUID orderId,@RequestBody @Valid OrderRequestDto request) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.ORDER_UPDATE_SUCCESS,
                orderService.updateOrder(orderId, request)));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<SuccessResponse<?>> deleteOrder(@PathVariable UUID orderId,
        @RequestHeader(value = "X-Username") String userName) {
        orderService.deleteOrder(orderId, userName);
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.ORDER_DELETE_SUCCESS));
    }

}
