package com.sparta.logistics.delivery.domain.repository;

import com.sparta.logistics.delivery.domain.model.Delivery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID>, DeliveryRepositoryCustom {

  @Query("SELECT d FROM Delivery d WHERE d.deliveryId = :deliveryId AND d.isDeleted = false")
  Optional<Delivery> findByDeliveryId(@Param("deliveryId") UUID deliveryId);

}
