package com.sparta.logistics.auth.libs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage{

    // global
    FIELD_VALIDATION_SUCCESS("필드 검증 성공"),

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
    LOGOUT_SUCCESS("로그아웃 성공"),
    USER_CREATE_SUCCESS("사용자 등록 성공"),
    USER_READ_SUCCESS("사용자 조회 성공"),
    USER_UPDATE_SUCCESS("사용자 수정 성공"),
    USER_DELETE_SUCCESS("사용자 삭제 성공"),
    VALID_USERNAME("사용 가능한 username 입니다."),
    VALID_EMAIL("사용 가능한 email 입니다."),
    VALID_SLACKID("사용 가능한 slackId 입니다.")

    ;
    private final String message;
}
