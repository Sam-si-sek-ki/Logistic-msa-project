package com.sparta.logistics.order.infrastructure.clinet;

import com.sparta.logistics.order.domain.model.Order;
import com.sparta.logistics.order.presentation.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "delivery-service", url = "${delivery-service.url}")
public interface DeliveryServiceClient {
    @PostMapping("/delivery")
    OrderResponseDto deliverOrder(Order order);
}