package com.sparta.logistics.product.presentation.dto;

import com.sparta.logistics.product.domain.model.Product;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {

    private UUID productId;
    private String productName;
    private int stockQuantity;
//    private UUID companyId;
//    private UUID hubId;
    private String description;

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .stockQuantity(product.getStockQuantity())
            .description(product.getDescription())
//            .hubId(product.getHubId())
//            .companyId(product.getCompanyId())
            .build();
    }

}
