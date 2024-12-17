package com.sparta.logistics.delivery.application.dto.delivery;


import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.client.company.CompanyClientResponse;
import com.sparta.logistics.delivery.infrastructure.client.order.OrderResponseDto;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateDeliveryRequest {

  private UUID orderId;
  private DeliveryStatus status;
  private UUID fromHubId;
  private UUID toHubId;
  private UUID receiverCompanyId;
  private String deliveryMainAddress;
  private String deliveryDetailAddress;
  private String recipientName;

  public static CreateDeliveryRequest of(
      OrderResponseDto orderResponseDto,
      CompanyClientResponse receiverCompany,
      CompanyClientResponse supplierCompany
  ) {
    return CreateDeliveryRequest.builder()
        .orderId(orderResponseDto.getOrderId())
        .status(DeliveryStatus.PENDING)
        .fromHubId(receiverCompany.getHubId())
        .toHubId(supplierCompany.getHubId())
        .deliveryMainAddress(receiverCompany.getCompanyMainAddress())
        .deliveryDetailAddress(receiverCompany.getCompanyDetailAddress())
        .recipientName(receiverCompany.getCompanyName())
        .build();
  }

  public Delivery toEntity() {
    return Delivery.builder()
        .orderId(this.orderId)
        .deliveryStatus(this.status)
        .fromHubId(this.fromHubId)
        .toHubId(this.toHubId)
        .deliveryMainAddress(this.deliveryMainAddress)
        .deliveryDetailAddress(this.deliveryDetailAddress)
        .recipientName(this.recipientName)
        .build();
  }
}
