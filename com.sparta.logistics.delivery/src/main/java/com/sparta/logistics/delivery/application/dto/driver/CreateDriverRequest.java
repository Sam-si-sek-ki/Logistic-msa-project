package com.sparta.logistics.delivery.application.dto.driver;

import com.sparta.logistics.delivery.domain.model.Driver;
import com.sparta.logistics.delivery.domain.model.DriverType;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CreateDriverRequest {

  @NotBlank
  private UUID hubId;
  @NotBlank
  private String username;
  @NotBlank
  private DriverType driverType;



  public Driver toEntity(Integer sequence) {
    return Driver.builder()
        .hubId(this.hubId)
        .username(this.username)
        .driverType(this.driverType)
        .build();
  }
}
