package com.sparta.logistics.delivery.application.dto.driver;

import com.sparta.logistics.delivery.domain.model.Driver;
import com.sparta.logistics.delivery.domain.model.DriverType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateDriverResponse {

  private UUID driverId;
  private UUID hub;
  private String username;
  private DriverType driverType;
  private Integer sequence;
  private LocalDateTime createdAt;

  public static CreateDriverResponse fromEntity(Driver driver) {
    return CreateDriverResponse.builder()
        .driverId(driver.getDriverId())
        .hub(driver.getHubId())
        .username(driver.getUsername())
        .driverType(driver.getDriverType())
        .sequence(driver.getSequence())
        .createdAt(driver.getCreatedAt())
        .build();
  }

}
