package com.sparta.logistics.delivery.infrastructure.client.order;

import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderResponseDto {
  private UUID orderId;
  private UUID receiverCompanyId;  // 수령업체ID
  private UUID supplierCompanyId;  // 공급업체ID
  private int orderQuantity;
}
