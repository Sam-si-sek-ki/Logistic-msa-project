package com.sparta.logistics.delivery.infrastructure.client;

import com.sparta.logistics.delivery.infrastructure.client.dto.HubTransferClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubTransferServiceClient {

  @GetMapping("/hub-transfers/{hubTransferId}")
  HubTransferClientResponse getHubTransfer(
      @PathVariable UUID hubTransferId);
}
