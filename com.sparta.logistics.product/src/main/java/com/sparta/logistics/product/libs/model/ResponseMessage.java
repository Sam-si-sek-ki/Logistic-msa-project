package com.sparta.logistics.product.libs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage{

    // global

    /**
     * 사용 예
     * **CUSTOM_NAME**("**MESSAGE**"), 등록 후
     * Controller 또는 Service단 에서 응답 데이터 예시
     * return ResponseEntity.ok().body(
     *      SuccessResponse.of(**CUSTOM_NAME**, CUSTOM_RESPONSE_DTO) // 여기서 CUSTOM_RESPONSE_DTO는 해당 도메인에서 응답에 담는 데이터DTO 입니다.
     * )
     */

    // domain
    PRODUCT_CREATE_SUCCESS("상품 등록 성공"),
    PRODUCT_GET_SUCCESS("상품 조회 성공"),
    PRODUCT_UPDATE_SUCCESS("상품 수정 성공"),
    PRODUCT_DELETE_SUCCESS("상품 삭제 성공"),
    PRODUCT_STOCK_DECREASE_SUCCESS("상품 재고량 감소 성공");

    private final String message;
}
