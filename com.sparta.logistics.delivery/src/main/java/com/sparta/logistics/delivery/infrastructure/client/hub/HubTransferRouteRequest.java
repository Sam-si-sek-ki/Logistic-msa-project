package com.sparta.logistics.delivery.infrastructure.client.hub;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HubTransferRouteRequest {

  private UUID fromHubId;
  private UUID toHubId;

}
