package com.sparta.logistics.company.libs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage{

    // global

    /**
     * 사용 예
     * **CUSTOM_NAME**("**MESSAGE**"), 등록 후
     *
     * Controller 또는 Service단 에서 응답 데이터 예시
     * return ResponseEntity.ok().body(
     *      SuccessResponse.of(**CUSTOM_NAME**, CUSTOM_RESPONSE_DTO) // 여기서 CUSTOM_RESPONSE_DTO는 해당 도메인에서 응답에 담는 데이터DTO 입니다.
     * )
     */

    // domain
    DELIVERY_SEARCH_SUCCESS("배송 조회가 완료되었습니다."),

    ;
    private final String message;
}
