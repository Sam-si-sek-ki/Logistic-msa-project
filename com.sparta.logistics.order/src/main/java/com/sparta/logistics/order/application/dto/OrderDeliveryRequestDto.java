package com.sparta.logistics.order.application.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderDeliveryRequestDto {
    private UUID orderId;
    private int orderQuantity;

    // 생성자, Getter, Setter
    public OrderDeliveryRequestDto(UUID orderId, int orderQuantity) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }
}
