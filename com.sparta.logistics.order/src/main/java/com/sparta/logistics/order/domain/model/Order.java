package com.sparta.logistics.order.domain.model;

import com.sparta.logistics.order.libs.model.BaseEntity;
import com.sparta.logistics.order.presentation.dto.OrderRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "p_order")
@SQLRestriction(value = "is_deleted = false")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId; // auto

    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private int orderQuantity;

    @Setter
    @Column()
    private UUID deliveryId;

    @Column(nullable = false)
    private UUID receiverCompanyId;

    @Column(nullable = false)
    private UUID supplierCompanyId;

    @Column(nullable = false)
    private String orderRequirements;

    private String productName;

    public static Order create(OrderRequestDto request, String productName) {
        return Order.builder()
            .productId(request.getProductId())
            .orderQuantity(request.getOrderQuantity())
            .productName(productName)
            .orderRequirements(request.getOrderRequirements())
            .build();
    }

    public void update(OrderRequestDto request) {
        this.productId = request.getProductId();
        this.orderQuantity = request.getOrderQuantity();
        this.orderRequirements = request.getOrderRequirements();
    }
}
