package com.sparta.logistics.product.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    @NotNull
    private String productName;

    @NotNull
    private String description;

    @NotNull
    private int stockQuantity;

    private UUID hubId;
    private UUID companyId;
}
