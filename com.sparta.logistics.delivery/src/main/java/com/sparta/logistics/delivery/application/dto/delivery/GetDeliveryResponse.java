package com.sparta.logistics.delivery.application.dto.delivery;


import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetDeliveryResponse {

  private UUID deliveryId;
  private UUID orderId;
  private DeliveryStatus status;
  private UUID startHubId;
  private UUID destinationHubId;
  private String mainAddress;
  private String detailAddress;
  private String recipientName;
  private String slackId;
  private LocalDateTime createdAt;

  public static GetDeliveryResponse fromEntity(Delivery delivery) {
    return GetDeliveryResponse.builder()
        .deliveryId(delivery.getDeliveryId())
        .orderId(delivery.getOrderId())
        .status(delivery.getDeliveryStatus())
        .startHubId(delivery.getFromHubId())
        .destinationHubId(delivery.getToHubId())
        .mainAddress(delivery.getDeliveryMainAddress())
        .detailAddress(delivery.getDeliveryDetailAddress())
        .recipientName(delivery.getRecipientName())
        .slackId(delivery.getSlackId())
        .createdAt(delivery.getCreatedAt())
        .build();
  }
}
