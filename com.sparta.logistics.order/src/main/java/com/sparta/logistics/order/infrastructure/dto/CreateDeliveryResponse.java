package com.sparta.logistics.order.infrastructure.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDeliveryResponse {
    private UUID deliveryId;
}