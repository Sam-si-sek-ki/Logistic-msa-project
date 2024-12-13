package com.sparta.logistics.auth.libs.model;

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

    // Auth
    LOGIN_SUCCESS("로그인 성공"),
    LOGIN_FAIL("로그인 실패"),
    USER_CREATE_SUCCESS("사용자 등록 성공"),

    ;
    private final String message;
}
