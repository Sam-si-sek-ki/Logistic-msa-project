package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.transferroute.CreateTransferRouteRequest;
import com.sparta.logistics.delivery.application.dto.transferroute.TransferRouteResponse;
import com.sparta.logistics.delivery.application.service.TransferRouteService;
import com.sparta.logistics.delivery.libs.model.ResponseMessage;
import com.sparta.logistics.delivery.libs.model.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer-routes")
@RequiredArgsConstructor
public class TransferRouteController {

  private final TransferRouteService transferRouteService;

  @PostMapping()
  public ResponseEntity<SuccessResponse<TransferRouteResponse>> createTransferRoute(
      @RequestBody CreateTransferRouteRequest request
  ) {
    return ResponseEntity.ok(
        SuccessResponse.of(
            ResponseMessage.TRANSFER_ROUTE_CREATE_SUCCESS,
            transferRouteService.createTransferRoute(request)
        )
    );
  }
}
