package com.sparta.logistics.delivery.domain.repository;

import com.sparta.logistics.delivery.domain.model.Delivery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

  Optional<Delivery> findByDeliveryIdAndDeletedFalse(UUID deliveryId);

}
