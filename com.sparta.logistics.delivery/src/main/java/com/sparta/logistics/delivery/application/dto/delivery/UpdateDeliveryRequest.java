package com.sparta.logistics.delivery.application.dto.delivery;

import com.sparta.logistics.delivery.domain.model.Delivery;
import java.util.UUID;
import lombok.Getter;

@Getter
public class UpdateDeliveryRequest {

  private UUID fromHubId;
  private UUID toHubId;
  private String deliveryMainAddress;
  private String deliveryDetailAddress;
  private String recipientName;

  public void updateDelivery(Delivery delivery) {
    Delivery.updateDeliveryInfo(
        fromHubId,
        toHubId,
        deliveryMainAddress,
        deliveryDetailAddress,
        recipientName
    );
  }
}
