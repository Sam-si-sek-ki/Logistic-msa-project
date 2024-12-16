package com.sparta.logistics.delivery.domain.model;

import com.sparta.logistics.delivery.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "p_driver")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Driver extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "driver_id", updatable = false, columnDefinition = "uuid")
  private UUID driverId;

  @Column(name = "hub_id", columnDefinition = "uuid", nullable = false)
  private UUID hubId;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "drive_type", nullable = false)
  private DriverType driverType;

  @Column(name = "sequence")
  private Integer sequence;

  @Builder
  public Driver(
      UUID driverId, UUID hubId, String username, DriverType driverType, Integer sequence){
    this.driverId = driverId;
    this.hubId = hubId;
    this.username = username;
    this.driverType = driverType;
    this.sequence = sequence;
  }
}
