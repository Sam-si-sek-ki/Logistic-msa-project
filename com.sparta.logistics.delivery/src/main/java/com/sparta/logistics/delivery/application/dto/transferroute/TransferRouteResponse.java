package com.sparta.logistics.delivery.application.dto.transferroute;


import com.sparta.logistics.delivery.domain.model.TransferRoute;
import com.sparta.logistics.delivery.domain.model.TransferStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransferRouteResponse {

  private UUID transferRouteId;
  private UUID deliveryId;
  private Integer sequence;
  private UUID fromHubId;
  private UUID toHubId;
  private Integer expectedDistance;
  private Integer expectedDuration;
  private Integer actualDistance;
  private Integer actualDuration;
  private TransferStatus transferStatus;
  private UUID driverId;

  public static TransferRouteResponse fromEntity(TransferRoute transferRoute) {
    return TransferRouteResponse.builder()
        .transferRouteId(transferRoute.getTransferRouteId())
        .deliveryId(transferRoute.getDeliveryId())
        .sequence(transferRoute.getSequence())
        .fromHubId(transferRoute.getFromHubId())
        .toHubId(transferRoute.getToHubId())
        .expectedDistance(transferRoute.getExpectedDistance())
        .expectedDuration(transferRoute.getExpectedDuration())
        .actualDistance(transferRoute.getActualDistance())
        .transferStatus(transferRoute.getTransferStatus())
        .driverId(transferRoute.getDriverId())
        .build();
  }
}
