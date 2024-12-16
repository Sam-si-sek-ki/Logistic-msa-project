package com.sparta.logistics.delivery.domain.model;

import com.sparta.logistics.delivery.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "p_transfer_route")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TransferRoute extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "transfer_route_id", columnDefinition = "uuid")
  private UUID transferRouteId;

  @Column(name = "delivery_id", nullable = false)
  private UUID deliveryId;

  @Column(name = "sequence", nullable = false)
  private Integer sequence;

  @Column(name = "from_hub_id", nullable = false, columnDefinition = "uuid")
  private UUID fromHubId;

  @Column(name = "to_hub_id", nullable = false, columnDefinition = "uuid")
  private UUID toHubId;

  @Column(name = "expected_distance", nullable = false)
  private Integer expectedDistance;

  @Column(name = "expected_duration", nullable = false)
  private Integer expectedDuration;

  @Column(name = "actual_distance", nullable = false)
  private Integer actualDistance;

  @Column(name = "actual_duration", nullable = false)
  private Integer actualDuration;

  @Column(name = "transfer_status", nullable = false)
  private TransferStatus transferStatus;

  @Column(name = "driver_id", nullable = false, columnDefinition = "uuid")
  private UUID driverId;

  public TransferRoute(UUID transferRouteId, UUID deliveryId, Integer sequence, UUID fromHubId,
      UUID toHubId, Integer expectedDistance, Integer expectedDuration, Integer actualDistance,
      Integer actualDuration, TransferStatus transferStatus, UUID driverId
  ) {
    this.transferRouteId = transferRouteId;
    this.deliveryId = deliveryId;
    this.sequence = sequence;
    this.fromHubId = fromHubId;
    this.toHubId = toHubId;
    this.expectedDistance = expectedDistance;
    this.expectedDuration = expectedDuration;
    this.actualDistance = actualDistance;
    this.actualDuration = actualDuration;
    this.transferStatus = transferStatus;
    this.driverId = driverId;
  }
}
