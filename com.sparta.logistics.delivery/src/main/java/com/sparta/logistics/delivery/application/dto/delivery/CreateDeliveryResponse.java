package com.sparta.logistics.delivery.application.dto.delivery;


import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateDeliveryResponse {

  private UUID deliveryId;
  private DeliveryStatus status;

  public static CreateDeliveryResponse fromEntity(Delivery delivery) {
    return CreateDeliveryResponse.builder()
        .deliveryId(delivery.getDeliveryId())
        .status(delivery.getDeliveryStatus())
        .build();
  }
}
