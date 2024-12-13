package com.sparta.logistics.order.presentation.dto;

import com.sparta.logistics.order.domain.model.Order;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponseDto {

    private UUID orderId;
    private UUID productId;
    private UUID deliveryId;
    private int orderQuantity;
    private UUID receiverCompanyId;
    private UUID supplierCompanyId;

    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
            .orderId(order.getOrderId())
            .productId(order.getProductId())
            .deliveryId(order.getDeliveryId())
            .orderQuantity(order.getOrderQuantity())
            .receiverCompanyId(order.getReceiverCompanyId())
            .supplierCompanyId(order.getSupplierCompanyId())
            .build();
    }
}
