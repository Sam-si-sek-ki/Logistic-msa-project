package com.sparta.logistics.notification.libs.model;

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

    // Notifications
    SLACK_MESSAGE_SEND_SUCCESS("슬랙 메시지 전송 성공"),
    CREATE_SUCCESS("등록 성공"),
    READ_SUCCESS("조회 성공"),
    UPDATE_SUCCESS("수정 성공"),
    DELETE_SUCCESS("삭제 성공")

    ;
    private final String message;
}
