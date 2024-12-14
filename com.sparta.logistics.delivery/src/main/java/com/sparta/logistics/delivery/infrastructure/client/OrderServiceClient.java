package com.sparta.logistics.delivery.infrastructure.client;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public class OrderServiceClient {

//  @GetMapping("/orders/{orderId}")
//  OrderResponse getOrder(@PathVariable UUID orderId);
}
