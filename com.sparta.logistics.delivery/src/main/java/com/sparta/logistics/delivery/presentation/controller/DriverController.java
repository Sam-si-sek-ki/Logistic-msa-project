package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.driver.CreateDriverRequest;
import com.sparta.logistics.delivery.application.dto.driver.CreateDriverResponse;
import com.sparta.logistics.delivery.application.dto.driver.GetDriverResponse;
import com.sparta.logistics.delivery.application.service.DriverService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/drivers")
@RequiredArgsConstructor
@RestController
public class DriverController {

  private final DriverService driverService;

  @PostMapping
  public ResponseEntity<CreateDriverResponse> createDriver(@RequestBody CreateDriverRequest request) {
    return ResponseEntity.ok(driverService.createDriver(request));
  }

  @GetMapping("{driverId}")
  public ResponseEntity<GetDriverResponse> getDriver(@PathVariable UUID driverId) {
    return ResponseEntity.ok(driverService.getDriver(driverId));
  }
}
