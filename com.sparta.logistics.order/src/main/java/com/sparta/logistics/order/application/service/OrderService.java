package com.sparta.logistics.order.application.service;

import com.sparta.logistics.order.application.dto.OrderDeliveryRequestDto;
import com.sparta.logistics.order.domain.model.Order;
import com.sparta.logistics.order.domain.repository.OrderRepository;
import com.sparta.logistics.order.infrastructure.client.CompanyClientResponse;
import com.sparta.logistics.order.infrastructure.client.CompanyServiceClient;
import com.sparta.logistics.order.infrastructure.client.DeliveryServiceClient;
import com.sparta.logistics.order.infrastructure.clinet.ProductServiceClient;
import com.sparta.logistics.order.infrastructure.dto.CreateDeliveryResponse;
import com.sparta.logistics.order.infrastructure.dto.ProductResponseDto;
import com.sparta.logistics.order.libs.exception.ErrorCode;
import com.sparta.logistics.order.libs.exception.GlobalException;
import com.sparta.logistics.order.presentation.dto.OrderRequestDto;
import com.sparta.logistics.order.presentation.dto.OrderResponseDto;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryServiceClient deliveryServiceClient;
    private final ProductServiceClient productServiceClient;
    private final CompanyServiceClient companyServiceClient;

    @Transactional
    public OrderResponseDto createOrder(@Valid OrderRequestDto request, String username,
        String role) {
        // 1. 상품 존재 여부 확인 및 재고 감소 + 상품 이름 반환
        ProductResponseDto productResponse = productServiceClient.validateAndDecreaseStock(
            request.getProductId(),
            request.getOrderQuantity()
        );
        // 상품 이름
        String productName = productResponse.getProductName();
        log.info("==== Received order request with name {}", productName);

        UUID receiverCompanyId = request.getReceiverCompanyId();
        UUID supplierCompanyId = request.getSupplierCompanyId();

        ResponseEntity<CompanyClientResponse> receiverCompany = companyServiceClient.getCompany(receiverCompanyId);
        ResponseEntity<CompanyClientResponse> supplierCompany = companyServiceClient.getCompany(supplierCompanyId);

        // 두 회사 모두 유효한지 확인
        if (receiverCompany.getStatusCode() != HttpStatus.OK ||
            supplierCompany.getStatusCode() != HttpStatus.OK) {
            throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        // 2. 주문 생성
        Order savedOrder = orderRepository.save(request.toEntity(productName));

        // 3. 배송 생성 요청 (배송 정보를 포함하는 DTO를 전달)
        OrderDeliveryRequestDto deliveryRequest = new OrderDeliveryRequestDto(
            savedOrder.getOrderId(),
            savedOrder.getOrderQuantity(),
            savedOrder.getReceiverCompanyId(),
            savedOrder.getSupplierCompanyId()
        );
        CreateDeliveryResponse deliveryResponse = deliveryServiceClient.createDelivery(
            deliveryRequest,
            username,
            role
        );

        // 4. 주문에 배송 ID 설정
        savedOrder.setDeliveryId(deliveryResponse.getDeliveryId());
        log.info("==== 배송 Id : " + savedOrder.getDeliveryId());
        // 5. 응답 DTO 반환
        return OrderResponseDto.from(savedOrder);
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
    public void deleteOrder(UUID orderId, String userName) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new GlobalException(ErrorCode.ORDER_NOT_FOUND));
        LocalDateTime deletedAt = LocalDateTime.now();
        order.setDelete(deletedAt, userName);
    }
}
