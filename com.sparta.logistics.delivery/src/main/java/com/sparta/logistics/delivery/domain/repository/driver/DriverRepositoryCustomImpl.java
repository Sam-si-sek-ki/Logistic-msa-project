package com.sparta.logistics.delivery.domain.repository.driver;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.delivery.domain.model.QDriver;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DriverRepositoryCustomImpl implements DriverRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public int findMaxSequenceByHubId(UUID hubId) {
    QDriver driver = QDriver.driver;

    return Optional.ofNullable(jpaQueryFactory
        .select(driver.sequence.max())
        .from(driver)
        .where(
            driver.hubId.eq(hubId),
            driver.isDeleted.eq(false)
        )
        .fetchOne())
        .orElse(0);
  }

  @Override
  public int countDriversByHubId(UUID hubId) {
    QDriver driver = QDriver.driver;

    return Math.toIntExact(Optional.ofNullable(jpaQueryFactory
            .select(driver.count())
            .from(driver)
            .where(
                driver.hubId.eq(hubId),
                driver.isDeleted.eq(false)
            )
            .fetchOne())
        .orElse(0L));
  }
}
