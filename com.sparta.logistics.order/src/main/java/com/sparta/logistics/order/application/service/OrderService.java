package com.sparta.logistics.order.application.service;

import com.sparta.logistics.order.domain.model.Order;
import com.sparta.logistics.order.domain.repository.OrderRepository;
import com.sparta.logistics.order.infrastructure.clinet.DeliveryServiceClient;
import com.sparta.logistics.order.libs.exception.ErrorCode;
import com.sparta.logistics.order.libs.exception.GlobalException;
import com.sparta.logistics.order.presentation.dto.OrderRequestDto;
import com.sparta.logistics.order.presentation.dto.OrderResponseDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryServiceClient deliveryServiceClient;

    @Transactional
    public OrderResponseDto createOrder(@Valid OrderRequestDto request) {
        // order 생성
        Order order = Order.create(request);

        Order savedOrder = orderRepository.save(order);
        OrderResponseDto deliveryResponse = deliveryServiceClient.deliverOrder(savedOrder);
        savedOrder.setDeliveryId(deliveryResponse.getDeliveryId());

        return OrderResponseDto.from(order);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new GlobalException(ErrorCode.ORDER_NOT_FOUND));
        return OrderResponseDto.from(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(UUID orderId, OrderRequestDto request) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new GlobalException(ErrorCode.ORDER_NOT_FOUND));

        order.update(request);
        return OrderResponseDto.from(order);
    }

    @Transactional
    public void deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new GlobalException(ErrorCode.ORDER_NOT_FOUND));
        // todo : 사용자 정보 받을 수 있을 떄 수정하기
//        order.setDelete();
    }
}
