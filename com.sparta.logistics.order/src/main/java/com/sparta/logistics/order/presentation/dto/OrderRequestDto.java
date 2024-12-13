package com.sparta.logistics.order.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    @NotNull
    private UUID productId;

    @NotNull
    private int orderQuantity;
}
