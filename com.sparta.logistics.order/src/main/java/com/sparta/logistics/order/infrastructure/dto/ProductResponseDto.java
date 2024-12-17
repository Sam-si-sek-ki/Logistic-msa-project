package com.sparta.logistics.order.infrastructure.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private UUID productId;
    private String productName;

    // Constructor, Getters, Setters
    public ProductResponseDto(UUID productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}
