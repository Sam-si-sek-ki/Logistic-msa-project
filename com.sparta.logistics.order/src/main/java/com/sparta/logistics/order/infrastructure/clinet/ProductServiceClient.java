package com.sparta.logistics.order.infrastructure.clinet;

import com.sparta.logistics.order.infrastructure.dto.StockDecreaseRequest;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// todo : url 수정하기
@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductServiceClient {

    @PostMapping("/products/{productId}/decrease-stock")
    void decreaseStock(@PathVariable UUID productId, @RequestBody StockDecreaseRequest request);
}