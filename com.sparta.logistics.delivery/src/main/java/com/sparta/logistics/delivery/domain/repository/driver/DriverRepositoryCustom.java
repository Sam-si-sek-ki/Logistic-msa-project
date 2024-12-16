package com.sparta.logistics.delivery.domain.repository.driver;

import java.util.UUID;

public interface DriverRepositoryCustom {

  int findMaxSequenceByHubId(UUID hubId);
  int countDriversByHubId(UUID hubId);
}
