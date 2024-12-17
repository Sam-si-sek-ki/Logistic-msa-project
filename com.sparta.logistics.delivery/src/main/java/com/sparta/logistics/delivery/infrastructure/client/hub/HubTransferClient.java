package com.sparta.logistics.delivery.infrastructure.client.hub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-service", contextId = "hubTransferClient")
public interface HubTransferClient {

  @GetMapping("/hub-transefers/route")
  ResponseEntity<HubTransferResponse> getHubTransfer(@RequestBody HubTransferRouteRequest request);
}
