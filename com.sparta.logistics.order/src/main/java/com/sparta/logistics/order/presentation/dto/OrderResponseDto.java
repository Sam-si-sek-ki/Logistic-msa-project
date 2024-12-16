package com.sparta.logistics.order.presentation.dto;

import com.sparta.logistics.order.domain.model.Order;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    private UUID orderId; // auto
    private UUID productId; // 첫 요청 시 입력받음
    private UUID deliveryId; // 배송 생성 클라이언트 요청 후 반환
    private int orderQuantity; // 첫 요청 시 입력받음
    private UUID receiverCompanyId; // 배송 생성 클라이언트 요청 후 반환?
    private UUID supplierCompanyId; // 배송 생성 클라이언트 요청 후 반환?
    private String productName; // product 클라이언트 : 존재여부 판단 후 이름 반환
    private String orderRequirements; // 첫 요청 시 입력받음

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
            .orderId(order.getOrderId())
            .productId(order.getProductId())
            .deliveryId(order.getDeliveryId())
            .orderQuantity(order.getOrderQuantity())
            .receiverCompanyId(order.getReceiverCompanyId())
            .supplierCompanyId(order.getSupplierCompanyId())
            .productName(order.getProductName())
            .orderRequirements(order.getOrderRequirements())
            .build();
    }
}
