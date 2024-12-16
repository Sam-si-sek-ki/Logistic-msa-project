package com.sparta.logistics.company.libs.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {

    private final ErrorCode errorCode;
}
