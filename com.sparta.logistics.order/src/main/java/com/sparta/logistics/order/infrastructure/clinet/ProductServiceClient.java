package com.sparta.logistics.order.infrastructure.clinet;

import com.sparta.logistics.order.infrastructure.dto.ProductResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @PostMapping("/products/{productId}/validate-and-decrease-stock")
    ProductResponseDto validateAndDecreaseStock(
        @PathVariable("productId") UUID productId,
        @RequestBody int orderQuantity
    );
}