package com.sparta.logistics.delivery.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "p_delivery")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "delivery_id", columnDefinition = "uuid")
  private UUID deliveryId;

  @Column(name = "order_id", columnDefinition = "uuid", nullable = false)
  private UUID orderId;

  @Column(name = "delivery_status", nullable = false)
  private DeliveryStatus deliveryStatus;

  @Column(name = "from_hub_id", nullable = false, columnDefinition = "uuid")
  private UUID fromHubId;

  @Column(name = "to_hub_id", nullable = false)
  private UUID toHubId;

  @Column(name = "delivery_main_address", nullable = false)
  private String deliveryMainAddress;

  @Column(name = "delivery_detail_address", nullable = false)
  private String deliveryDetailAddress;

  @Column(name = "recipient_name", nullable = false)
  private String recipientName;

  @Column(name = "slack_id", nullable = false)
  private String slackId;

  @Builder
  public Delivery (UUID deliveryId, UUID orderId, DeliveryStatus deliveryStatus, UUID fromHubId,
      UUID toHubId, String deliveryMainAddress, String deliveryDetailAddress, String recipientName,
      String slackId
      ) {
    this.deliveryId = deliveryId;
    this.orderId = orderId;
    this.deliveryStatus = deliveryStatus;
    this.fromHubId = fromHubId;
    this.toHubId = toHubId;
    this.deliveryMainAddress = deliveryMainAddress;
    this.deliveryDetailAddress = deliveryDetailAddress;
    this.recipientName = recipientName;
    this.slackId = slackId;

  }
}
