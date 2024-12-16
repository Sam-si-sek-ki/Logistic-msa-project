package com.sparta.logistics.product.presentation.controller;

import com.sparta.logistics.product.application.dto.StockRequestDto;
import com.sparta.logistics.product.application.service.ProductService;
import com.sparta.logistics.product.domain.model.Product;
import com.sparta.logistics.product.libs.model.ResponseMessage;
import com.sparta.logistics.product.libs.model.SuccessResponse;
import com.sparta.logistics.product.presentation.dto.ProductRequestDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@DynamicUpdate
public class ProductController {

    private final ProductService productService;

    @PostMapping
//    @PreAuthorize("hasRole('MASTER_ADMIN') or (hasRole('HUB_ADMIN')) or (hasRole('COMPANY_USER'))")
    public ResponseEntity<SuccessResponse<Product>> createProduct(
        @RequestBody @Valid ProductRequestDto request,
        @RequestHeader(value = "X-USER-NAME", required = false) String userName,
        @RequestHeader(value = "X-USER-ROLE", required = false) String userRole) {

        // test 사용자 정보
        userName = "testUser";
        userRole = "test_manager";
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.PRODUCT_CREATE_SUCCESS,
                productService.createProduct(request, userName, userRole)));
    }
    //단건 조회
    @GetMapping("/{productId}")
    public ResponseEntity<SuccessResponse<?>> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.PRODUCT_GET_SUCCESS,
                productService.getProductById(productId)));
    }

    @PutMapping("/{productId}")
//    @PreAuthorize("hasRole('MASTER_ADMIN') or (hasRole('HUB_ADMIN')) or (hasRole('COMPANY_USER'))")
    public ResponseEntity<SuccessResponse<?>> updateProduct(@PathVariable UUID productId,
        @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.PRODUCT_UPDATE_SUCCESS,
            productService.updateProduct(productId, request)));
    }

    @DeleteMapping("/{productId}")
//    @PreAuthorize("hasRole('MASTER_ADMIN') or (hasRole('HUB_ADMIN'))")
    public ResponseEntity<SuccessResponse<?>> deleteProduct(@PathVariable UUID productId) {
        productService.softDeleteProduct(productId);
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.PRODUCT_DELETE_SUCCESS));
    }

    // 주문 시 재고량 감소
    @PostMapping("/{productId}/decrease-stock")
    public ResponseEntity<SuccessResponse<?>> decreaseStock(
        @PathVariable UUID productId,
        @RequestBody @Valid StockRequestDto request
    ) {
        productService.decreaseStock(productId, request.getOrderQuantity());
        return ResponseEntity.ok()
            .body(SuccessResponse.of(ResponseMessage.PRODUCT_STOCK_DECREASE_SUCCESS));
    }
}
