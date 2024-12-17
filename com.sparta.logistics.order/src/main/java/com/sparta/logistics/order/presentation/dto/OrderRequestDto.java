package com.sparta.logistics.order.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    @NotNull private UUID productId;
    @NotNull private int orderQuantity;
    @NotNull private String orderRequirements;
    @NotNull private UUID receiverCompanyId;
    @NotNull private UUID supplierCompanyId;
}
