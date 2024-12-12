package com.sparta.logistics.product.domain.repository;

import com.sparta.logistics.product.domain.model.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
