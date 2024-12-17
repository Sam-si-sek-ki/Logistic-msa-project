package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryRequest;
import com.sparta.logistics.delivery.application.dto.delivery.CreateDeliveryResponse;
import com.sparta.logistics.delivery.application.dto.delivery.GetDeliveryResponse;
import com.sparta.logistics.delivery.application.dto.delivery.UpdateDeliveryRequest;
import com.sparta.logistics.delivery.application.validation.DeliveryValidation;
import com.sparta.logistics.delivery.domain.model.Delivery;
import com.sparta.logistics.delivery.domain.model.DeliveryStatus;
import com.sparta.logistics.delivery.domain.repository.delivery.DeliveryRepository;
import com.sparta.logistics.delivery.infrastructure.client.company.CompanyClientResponse;
import com.sparta.logistics.delivery.infrastructure.client.company.CompanyServiceClient;
import com.sparta.logistics.delivery.infrastructure.client.order.OrderResponseDto;
import com.sparta.logistics.delivery.infrastructure.client.user.UserResponse;
import com.sparta.logistics.delivery.infrastructure.client.user.UserServiceClient;
import com.sparta.logistics.delivery.infrastructure.configuration.UserContextHolder;
import com.sparta.logistics.delivery.libs.exception.ErrorCode;
import com.sparta.logistics.delivery.libs.exception.GlobalException;
import com.sparta.logistics.delivery.libs.model.SuccessResponse;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CompanyServiceClient companyServiceClient;
    private final UserServiceClient userServiceClient;
    private final DeliveryValidation deliveryValidation;
    private final UserContextHolder userContextHolder;

    @Transactional
    public CreateDeliveryResponse createDelivery(OrderResponseDto orderResponseDto,
        String h_username, String role) {

        String username = userContextHolder.getCurrentAuditor();
        log.info("Request headers - X-Username: {}, X-Role: {}", username, role);

        String slackId = Optional.ofNullable(userServiceClient.getUserByUsername(username, username, role))
            .map(ResponseEntity::getBody)         // SuccessResponse 얻기
            .map(SuccessResponse::data)        // UserResponse 얻기
            .map(UserResponse::getSlackId)        // slackId 얻기
            .orElseThrow(() -> new GlobalException(ErrorCode.SLACK_ID_NOT_FOUND));

        log.info("Request headers - X-Username: {}, X-Role: {}", username, role);

        // 1. 수령 업체 정보 조회
        CompanyClientResponse receiverCompany = companyServiceClient.getCompany(
            orderResponseDto.getReceiverCompanyId(),
            userContextHolder.getCurrentAuditor()
        );

        // 2. 공급 업체 정보 조회
        CompanyClientResponse supplierCompany = companyServiceClient.getCompany(
            orderResponseDto.getSupplierCompanyId(),
            userContextHolder.getCurrentAuditor()
        );

        CreateDeliveryRequest request = CreateDeliveryRequest.of(
            orderResponseDto,
            receiverCompany,
            supplierCompany,
            slackId
        );

        deliveryValidation.createDeliveryValidation(request);

        Delivery delivery = request.toEntity();
        delivery = deliveryRepository.save(delivery);
        return CreateDeliveryResponse.fromEntity(delivery);
    }

    @Transactional
    public GetDeliveryResponse updateDelivery(UUID deliveryId, UpdateDeliveryRequest request) {

        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCode.DELIVERY_NOT_FOUND));

        deliveryValidation.updateDeliveryValidation(request, delivery);

        request.updateDelivery(delivery);

        return GetDeliveryResponse.fromEntity(delivery);
    }

    public void deleteDelivery(UUID deliveryId, Long userId) {

        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCode.DELIVERY_NOT_FOUND));

        delivery.setDelete(LocalDateTime.now(), userId.toString());
    }

    public GetDeliveryResponse getDelivery(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCode.DELIVERY_NOT_FOUND));

        return GetDeliveryResponse.fromEntity(delivery);
    }

    public List<GetDeliveryResponse> findDeliveries(DeliveryStatus status) {
        if (status == null) {
            return Collections.emptyList(); // 또는 전체 조회 로직
        }
        return deliveryRepository.findByStatus(status).stream()
            .map(GetDeliveryResponse::fromEntity)
            .collect(Collectors.toList());
    }
}
