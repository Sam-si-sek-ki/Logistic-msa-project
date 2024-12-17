package com.sparta.logistics.delivery.infrastructure.client.order;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

//  @GetMapping("/orders/{orderId}")
//  OrderResponse getOrder(@PathVariable UUID orderId);
}
