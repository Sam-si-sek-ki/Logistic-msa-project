package com.sparta.logistics.delivery.domain.repository.driver;

import com.sparta.logistics.delivery.domain.model.Driver;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID>, DriverRepositoryCustom {

  @Query("SELECT d FROM Driver d WHERE d.driverId = :driverId AND d.isDeleted = false")
  Optional<Driver> findByDriverId(@Param("driverId") UUID driverId);
}
