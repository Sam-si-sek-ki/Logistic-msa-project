package com.sparta.logistics.notification.libs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // global
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    NOT_AUTHENTICATED_USER(HttpStatus.UNAUTHORIZED, "인증된 사용자가 아닙니다."),
    UNEXPECTED_PRINCIPAL_TYPE(HttpStatus.BAD_REQUEST, "예상치 못한 Principal 타입입니다."),
    INVALID_FIELD(HttpStatus.BAD_REQUEST, "잘못된 필드입니다."),

    // Notifications
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 정보를 찾을 수 없습니다."),
    SLACK_MESSAGE_SEND_FAILED(HttpStatus.SERVICE_UNAVAILABLE, "슬랙 메시지 전송에 실패하였습니다."),


    /**
     * 사용 예
     * **CUSTOM_NAME**(HttpStatus.**ENUM_NAME**,"**MESSAGE**"),
     * 위 코드를 작성하고
     * throw new GlobalException(**CUSTOM_NAME**));
     * 위 코드와 같이 throw 요청시 처리됩니다.
     *
     * ex) HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 허브를 찾을 수 없습니다.")
     * ex) throw new GlobalException(HUB_NOT_FOUND)
     */

    ;
    private final HttpStatus httpStatus;
    private final String description;
}