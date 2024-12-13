package com.sparta.logistics.product.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockDecreaseRequest {
    private int orderQuantity; // feign client dto
}
