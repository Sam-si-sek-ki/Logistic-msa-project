package com.sparta.logistics.delivery.domain.repository.transferroute;

import com.sparta.logistics.delivery.domain.model.TransferRoute;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRouteRepository extends JpaRepository<TransferRoute, UUID> {

  List<TransferRoute> findByDeliveryIdOrderBySequenceAsc(UUID deliveryId);
}
