package com.sparta.logistics.order.presentation.dto;

import com.sparta.logistics.order.domain.model.Order;
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

    public Order toEntity(String productName) {
        return Order.builder()
            .productId(this.productId)
            .orderQuantity(this.orderQuantity)
            .receiverCompanyId(this.receiverCompanyId)
            .supplierCompanyId(this.supplierCompanyId)
            .productName(productName)
            .build();
    }
}
