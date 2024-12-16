package com.sparta.logistics.delivery.domain.repository.delivery;

import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import java.util.List;

public interface DeliveryRepositoryCustom {

  List<Delivery> findByStatus(DeliveryStatus status);

}
