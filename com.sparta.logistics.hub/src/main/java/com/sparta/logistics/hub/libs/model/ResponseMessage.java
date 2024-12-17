package com.sparta.logistics.hub.libs.model;

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
    HUB_SELECT_SUCCESS("허브 조회 성공"),
    HUB_CREATE_SUCCESS("허브 등록 성공"),
    HUB_UPDATE_SUCCESS("허브 등록 성공"),
    HUB_DELETE_SUCCESS("허브 등록 성공"),

    HUB_TRANSFER_SELECT_SUCCESS("허브이동관리 조회 성공"),
    HUB_TRANSFER_CREATE_SUCCESS("허브이동관리 생성 성공"),
    HUB_TRANSFER_DELETE_SUCCESS("허브이동관리 삭제 성공"),

    HUB_CONNECTION_INFO_SELECT_SUCCESS("허브연결정보 조회 성공"),
    HUB_CONNECTION_INFO_CREATE_SUCCESS("허브연결정보 생성 성공"),
    HUB_CONNECTION_INFO_DELETE_SUCCESS("허브연결정보 삭제 성공"),

    ;
    private final String message;
}
