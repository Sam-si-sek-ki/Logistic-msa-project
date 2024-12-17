package com.sparta.logistics.delivery.application.validation;

import com.sparta.logistics.delivery.application.dto.driver.CreateDriverRequest;
import com.sparta.logistics.delivery.infrastructure.client.hub.HubServiceClient;
import com.sparta.logistics.delivery.infrastructure.client.hub.HubClientResponse;
import com.sparta.logistics.delivery.libs.exception.ErrorCode;
import com.sparta.logistics.delivery.libs.exception.GlobalException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverValidation {

  private final HubServiceClient hubServiceClient;

  public void validateCreateDriver(CreateDriverRequest request, UUID hubId) {

    ResponseEntity<HubClientResponse> hubResponse = hubServiceClient.getHub(hubId);
    if (!hubResponse.getStatusCode().is2xxSuccessful()) {
      throw new GlobalException(ErrorCode.HUB_NOT_FOUND);
    }
  }
}
