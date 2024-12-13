package com.sparta.logistics.product.presentation.controller;

import com.sparta.logistics.product.application.dto.StockDecreaseRequest;
import com.sparta.logistics.product.application.service.ProductService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@DynamicUpdate
public class ProductController {

    private final ProductService productService;

    @PostMapping
    // todo : 담당 권한이 있는지 확인필요
    public ResponseEntity<SuccessResponse<?>> createUser(
        @RequestBody @Valid ProductRequestDto request) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.PRODUCT_CREATE_SUCCESS,
                productService.createProduct(request)));
    }

    @GetMapping("/{productId}")
    // todo : 자신의 상품인지? 확인필요
    public ResponseEntity<SuccessResponse<?>> getProducts(@PathVariable UUID productId) {
        return ResponseEntity.ok().body(
            SuccessResponse.of(ResponseMessage.PRODUCT_GET_SUCCESS,
                productService.getProduct(productId)));
    }

    @PutMapping("/{productId}")
    // todo : 담당 권한이 있는지 확인필요
    public ResponseEntity<SuccessResponse<?>> updateProduct(@PathVariable UUID productId,
        @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.PRODUCT_UPDATE_SUCCESS,
            productService.updateProduct(productId, request)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<SuccessResponse<?>> deleteProduct(@PathVariable UUID productId) {
        productService.softDeleteProduct(productId);
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.PRODUCT_DELETE_SUCCESS));
    }
    // todo : search 추가


    // 주문 시 재고량 감소
    @PostMapping("/{productId}/decrease-stock")
    public ResponseEntity<SuccessResponse<?>> decreaseStock(
        @PathVariable UUID productId,
        @RequestBody @Valid StockDecreaseRequest request
    ) {
        productService.decreaseStock(productId, request.getOrderQuantity());
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.PRODUCT_STOCK_DECREASE_SUCCESS));
    }
}
