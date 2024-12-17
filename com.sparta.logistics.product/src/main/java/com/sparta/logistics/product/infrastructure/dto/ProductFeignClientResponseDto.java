package com.sparta.logistics.product.infrastructure.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductFeignClientResponseDto {
    private UUID productId;
    private String productName;

    // Constructor, Getters, Setters
    public ProductFeignClientResponseDto(UUID productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}