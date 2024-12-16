package com.sparta.logistics.delivery.application.dto.driver;

import com.sparta.logistics.delivery.domain.model.Driver;
import com.sparta.logistics.delivery.domain.model.DriverType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetDriverResponse {

  private UUID driverId;
  private UUID hubId;
  private String username;
  private DriverType driverType;
  private Integer sequence;
  private LocalDateTime createdAt;

  public static GetDriverResponse fromEntity(Driver driver) {
    return GetDriverResponse.builder()
        .driverId(driver.getDriverId())
        .hubId(driver.getHubId())
        .username(driver.getUsername())
        .sequence(driver.getSequence())
        .createdAt(driver.getCreatedAt())
        .build();
  }
}
