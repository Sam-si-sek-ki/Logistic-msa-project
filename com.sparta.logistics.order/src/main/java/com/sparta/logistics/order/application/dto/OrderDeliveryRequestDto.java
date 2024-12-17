package com.sparta.logistics.order.application.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeliveryRequestDto {
    private UUID orderId;
    private int orderQuantity;
}
