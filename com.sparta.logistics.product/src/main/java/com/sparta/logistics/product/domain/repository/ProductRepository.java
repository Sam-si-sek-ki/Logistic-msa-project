package com.sparta.logistics.product.domain.repository;

import com.sparta.logistics.product.domain.model.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, UUID>{

}
