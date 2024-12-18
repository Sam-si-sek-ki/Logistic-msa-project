package com.sparta.logistics.delivery.infrastructure.client.hub;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service", contextId = "hubClient")
public interface HubServiceClient {

  @GetMapping("/hubs/{hubId}")
  ResponseEntity<HubClientResponse> getHub(@PathVariable UUID hubId);
}
