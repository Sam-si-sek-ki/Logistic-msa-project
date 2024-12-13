package com.sparta.logistics.order.libs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    ORDER_CREATE_SUCCESS("주문 등록 성공"),
    ORDER_GET_SUCCESS("주문 조회 성공"),
    ORDER_UPDATE_SUCCESS("주문 수정 성공"),
    ORDER_DELETE_SUCCESS("주문 삭제 성공");
    private final String message;
}
