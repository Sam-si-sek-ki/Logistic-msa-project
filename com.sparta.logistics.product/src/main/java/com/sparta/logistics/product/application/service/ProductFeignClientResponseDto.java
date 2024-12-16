package com.sparta.logistics.product.application.service;

import java.util.UUID;

public class ProductFeignClientResponseDto {
    private UUID productId;
    private String productName;

    // Constructor, Getters, Setters
    public ProductFeignClientResponseDto(UUID productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}