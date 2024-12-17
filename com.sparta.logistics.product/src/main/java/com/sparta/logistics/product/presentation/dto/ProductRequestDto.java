package com.sparta.logistics.product.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    @NotNull private String productName;
    @NotNull private String description;
    @NotNull private int stockQuantity;
    @NotNull private UUID companyId;
}
