package com.sparta.logistics.delivery.application.validation;

import com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryRequest;
import com.sparta.logistics.delivery.application.dto.delivery.UpdateDeliveryRequest;
import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import com.sparta.logistics.delivery.libs.exception.ErrorCode;
import com.sparta.logistics.delivery.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryValidation {

  public void createDeliveryValidation(CreateDeliveryRequest request){
    // TODO: 세부 권한 확인 필요

    if (request.getFromHubId() == null || request.getToHubId() == null) {
      throw new GlobalException(ErrorCode.INVALID_HUB_INFO);
    }

    if (request.getFromHubId().equals(request.getToHubId())) {
      throw new GlobalException(ErrorCode.INVALID_HUB_ROUTE);
    }
  }

  public void updateDeliveryValidation(UpdateDeliveryRequest request, Delivery delivery) {

    if (!DeliveryStatus.PENDING.equals(delivery.getDeliveryStatus())) {
      throw new GlobalException(ErrorCode.INVALID_DELIVERY_STATUS);
    }

    if (request.getFromHubId().equals(request.getToHubId())) {
      throw new GlobalException(ErrorCode.INVALID_HUB_ROUTE);
    }
  }
}
