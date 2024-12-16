package com.sparta.logistics.order.infrastructure.clinet;



import com.sparta.logistics.order.application.dto.OrderDeliveryRequestDto;
import com.sparta.logistics.order.infrastructure.dto.CreateDeliveryResponse;
import com.sparta.logistics.order.presentation.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service")
public interface DeliveryServiceClient {

    // 배송 생성 요청
    @PostMapping("/delivery")
    CreateDeliveryResponse createDelivery(@RequestBody OrderDeliveryRequestDto deliveryRequest);
}
