package com.sparta.logistics.product.presentation.controller;

import com.sparta.logistics.product.application.service.ProductService;
import com.sparta.logistics.product.presentation.dto.ProductRequestDto;
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
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{productId}")
    // todo : 자신의 상품인지? 확인필요
    public ResponseEntity<?> getProducts(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PutMapping("/{productId}")
    // todo : 담당 권한이 있는지 확인필요
    public ResponseEntity<?> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.updateProduct(productId, request));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok().build();
    }
}
