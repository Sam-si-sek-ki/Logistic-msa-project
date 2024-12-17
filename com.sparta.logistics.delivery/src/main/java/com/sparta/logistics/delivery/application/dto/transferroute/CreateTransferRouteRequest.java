package com.sparta.logistics.delivery.application.dto.transferroute;

import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.Driver;
import com.sparta.logistics.delivery.domain.model.TransferRoute;
import com.sparta.logistics.delivery.domain.model.TransferStatus;
import com.sparta.logistics.delivery.infrastructure.client.hub.HubTransferResponse;
import java.util.UUID;
import lombok.Getter;


@Getter
public class CreateTransferRouteRequest {

  private UUID deliveryId;

  public TransferRoute toEntity(
      Delivery delivery,
      Driver driver,
      HubTransferResponse hubTransfer) {
    return TransferRoute.builder()
        .deliveryId(delivery.getDeliveryId())
        .sequence(driver.getSequence())
        .fromHubId(delivery.getFromHubId())
        .toHubId(delivery.getToHubId())
        .expectedDistance(hubTransfer.getDistance())
        .expectedDuration(hubTransfer.getDuration())
        .driverId(driver.getDriverId())
        .transferStatus(TransferStatus.PENDING)
        .build();
  }
}
