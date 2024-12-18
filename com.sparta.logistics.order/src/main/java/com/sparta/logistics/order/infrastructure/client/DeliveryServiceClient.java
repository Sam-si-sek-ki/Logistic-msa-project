package com.sparta.logistics.order.infrastructure.client;

import com.sparta.logistics.order.application.dto.OrderDeliveryRequestDto;
import com.sparta.logistics.order.infrastructure.dto.CreateDeliveryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "delivery-service")
public interface DeliveryServiceClient {

    @PostMapping("/deliveries")
    CreateDeliveryResponse createDelivery(@RequestBody OrderDeliveryRequestDto deliveryRequest,
    @RequestHeader("X-Username") String username,
        @RequestHeader("X-Role") String role);

}
