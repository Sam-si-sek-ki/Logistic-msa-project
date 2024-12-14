package com.sparta.logistics.delivery.infrastructure.client.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class HubTransferClientResponse {
  private UUID fromHubId;
  private UUID toHubId;
}
