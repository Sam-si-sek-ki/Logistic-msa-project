package com.sparta.logistics.product.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDto {
    private int orderQuantity; // feign client dto
}
