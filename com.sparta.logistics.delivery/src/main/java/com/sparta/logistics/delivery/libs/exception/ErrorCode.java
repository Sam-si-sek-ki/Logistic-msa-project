package com.sparta.logistics.delivery.libs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // global
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    NOT_AUTHENTICATED_USER(HttpStatus.UNAUTHORIZED, "인증된 사용자가 아닙니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    UNEXPECTED_PRINCIPAL_TYPE(HttpStatus.BAD_REQUEST, "예상치 못한 Principal 타입입니다."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "외부 서비스와 통신 중 오류가 발생했습니다."),

    // delivery
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회사를 찾을 수 없습니다."),
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 허브를 찾을 수 없습니다."),
    DELIVERY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 배송을 찾을 수 없습니다."),
    DRIVER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 배송을 찾을 수 없습니다."),
    HUB_TRANSFER_NOT_FOUND(HttpStatus.BAD_REQUEST, "배송 상태가 PENDING일 경우에만 배송 정보를 수정할 수 있습니다."),

    INVALID_DELIVERY_INFO(HttpStatus.BAD_REQUEST, "배송 정보가 유효하지 않습니다."),
    INVALID_HUB_INFO(HttpStatus.BAD_REQUEST, "허브 정보가 유효하지 않습니다."),
    INVALID_HUB_ROUTE(HttpStatus.BAD_REQUEST, "출발지와 도착지 허브가 동일할 수 없습니다."),
    INVALID_DELIVERY_STATUS(HttpStatus.BAD_REQUEST, "배송 상태가 PENDING일 경우에만 배송 정보를 수정할 수 있습니다."),
    SLACK_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자 slack ID가 존재하지 않습니다.");






    private final HttpStatus httpStatus;
    private final String description;
}