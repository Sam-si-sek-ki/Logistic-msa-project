package com.sparta.logistics.product.application.service;

import com.sparta.logistics.product.domain.model.Product;
import com.sparta.logistics.product.domain.repository.ProductRepository;
import com.sparta.logistics.product.libs.exception.ErrorCode;
import com.sparta.logistics.product.libs.exception.GlobalException;
import com.sparta.logistics.product.presentation.dto.ProductRequestDto;
import com.sparta.logistics.product.presentation.dto.ProductResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(ProductRequestDto request) {
    // todo : 사용자 인증 및 권한 확인
        Product product = Product.create(request);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(UUID productId) {
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
    public void softDeleteProduct(UUID productId) {
        // todo : 권한 + 유저확인 후 로직 실행되도록 변경 
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));
        // todo : 게이트웨이로 부터 정보를 받아오면 수정하기
        // product.setDelete();
    }

    // 주문 시 상품 존재 여부 확인 및 재고량 감소
    @Transactional
    public void decreaseStock(UUID productId, int orderQuantity) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PRODUCT_NOT_FOUND));

        if (product.getStockQuantity() < orderQuantity) {
            throw new GlobalException(ErrorCode.PRODUCT_INSUFFICIENT_STOCK);
        }
        product.setStockQuantity(product.getStockQuantity() - orderQuantity);
        productRepository.save(product);
    }
}
