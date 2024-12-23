package com.sparta.logistics.product.application.service;

import com.sparta.logistics.product.domain.model.Product;
import com.sparta.logistics.product.domain.repository.ProductRepository;
import com.sparta.logistics.product.infrastructure.client.CompanyFeignClient;
import com.sparta.logistics.product.infrastructure.dto.CompanyFeignClientResponse;
import com.sparta.logistics.product.infrastructure.dto.ProductFeignClientResponseDto;
import com.sparta.logistics.product.libs.exception.ErrorCode;
import com.sparta.logistics.product.libs.exception.GlobalException;
import com.sparta.logistics.product.presentation.dto.ProductRequestDto;
import com.sparta.logistics.product.presentation.dto.ProductResponseDto;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyFeignClient companyFeignClient;

    @Transactional
    public Product createProduct(ProductRequestDto request) {
        // 1. 업체 존재 여부 및 허브 아이디 확인
        ResponseEntity<CompanyFeignClientResponse> companyResponse = companyFeignClient.checkCompanyExistence(
            request.getCompanyId());
        if (!companyResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("상품 등록 권한 또는 업체 정보가 없습니다.");
        }
        UUID hubId = Objects.requireNonNull(companyResponse.getBody()).getHubId();
        String companyName = companyResponse.getBody().getCompanyName();
        Product product = Product.create(request, hubId, companyName);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));
        return ProductResponseDto.from(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(UUID productId, ProductRequestDto request) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));

        product.update(request);
        return ProductResponseDto.from(product);
    }

    @Transactional
    public void softDeleteProduct(UUID productId, String username) {
        // todo : 권한 + 유저확인 후 로직 실행되도록 변경 
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));
        LocalDateTime deletedAt = LocalDateTime.now();
        product.setDelete(deletedAt, username);
    }

    // 주문 시 상품 존재 여부 확인 및 재고량 감소
    @Transactional
    public ProductFeignClientResponseDto validateAndDecreaseStock(UUID productId,
        int orderQuantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));

        if (product.getStockQuantity() < orderQuantity) {
            throw new GlobalException(ErrorCode.PRODUCT_INSUFFICIENT_STOCK);
        }
        product.setStockQuantity(product.getStockQuantity() - orderQuantity);
        productRepository.save(product);
        // 상품 정보 반환
        return new ProductFeignClientResponseDto(product.getProductId(), product.getProductName());
    }
}
