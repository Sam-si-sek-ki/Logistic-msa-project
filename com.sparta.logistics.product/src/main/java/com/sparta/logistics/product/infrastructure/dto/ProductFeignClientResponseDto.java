package com.sparta.logistics.product.infrastructure.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFeignClientResponseDto {
    private UUID productId;
    private String productName;

}