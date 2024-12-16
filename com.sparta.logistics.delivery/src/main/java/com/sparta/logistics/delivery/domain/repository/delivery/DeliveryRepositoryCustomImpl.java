package com.sparta.logistics.delivery.domain.repository.delivery;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import com.sparta.logistics.delivery.domain.model.QDelivery;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<Delivery> findByStatus(DeliveryStatus status) {
    QDelivery delivery = QDelivery.delivery;

    return jpaQueryFactory
        .selectFrom(delivery)
        .where(delivery.deliveryStatus.eq(status))
        .fetch();
  }
}
