package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.driver.CreateDriverRequest;
import com.sparta.logistics.delivery.application.dto.driver.CreateDriverResponse;
import com.sparta.logistics.delivery.application.dto.driver.GetDriverResponse;
import com.sparta.logistics.delivery.application.validation.DriverValidation;
import com.sparta.logistics.delivery.domain.model.Driver;
import com.sparta.logistics.delivery.domain.repository.driver.DriverRepository;
import com.sparta.logistics.delivery.libs.exception.ErrorCode;
import com.sparta.logistics.delivery.libs.exception.GlobalException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DriverService {

  private final DriverRepository driverRepository;
  private final DriverValidation driverValidation;

  public CreateDriverResponse createDriver(CreateDriverRequest request) {
    driverValidation.validateCreateDriver(request, request.getHubId());

    int sequence = calculateNextSequence(request.getHubId());
    Driver driver = request.toEntity(sequence);
    Driver savedDriver = driverRepository.save(driver);

    return CreateDriverResponse.fromEntity(savedDriver);
  }

  private int calculateNextSequence(UUID hubId) {
    int totalDrivers = driverRepository.countDriversByHubId(hubId);
    int maxSequence = driverRepository.findMaxSequenceByHubId(hubId);

    return (totalDrivers == 0) ? 1: (maxSequence % totalDrivers);
  }

  public GetDriverResponse getDriver(UUID driverId) {
    Driver driver = driverRepository.findByDriverId(driverId)
        .orElseThrow(() -> new GlobalException(ErrorCode.DRIVER_NOT_FOUND));

    return GetDriverResponse.fromEntity(driver);
  }
}
