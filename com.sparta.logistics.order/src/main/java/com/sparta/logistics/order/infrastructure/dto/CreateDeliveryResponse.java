package com.sparta.logistics.order.infrastructure.dto;

import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateDeliveryResponse {

    private UUID deliveryId;
    private DeliveryStatus status;

    public static com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryResponse fromEntity(
        Delivery delivery) {
        return com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryResponse.builder()
            .deliveryId(delivery.getDeliveryId())
            .status(delivery.getDeliveryStatus())
            .build();
    }
}