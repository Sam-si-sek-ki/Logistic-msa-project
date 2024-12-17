package com.sparta.logistics.notification.libs.exception;

import static com.sparta.logistics.notification.libs.exception.ErrorCode.BAD_REQUEST;
import static com.sparta.logistics.notification.libs.exception.ErrorCode.INTERNAL_SERVER_ERROR;

import com.sparta.logistics.notification.libs.model.ErrorResponse;
import com.sparta.logistics.notification.libs.model.ErrorResponse.ValidationError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * GlobalException(커스텀 에러) 처리
     *
     * @param e GlobalException
     * @return ResponseEntity
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> handleCustomException(GlobalException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("GlobalException occurred : ErrorCode = {} message = {}",
                  errorCode.name(), errorCode.getDescription());
        return handleExceptionInternal(errorCode);
    }

    /**
     * MethodArgumentNotValidException 처리 (Validation 실패 등)
     *
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        // 모든 유효성 검증 오류를 가져와서 메시지를 구성
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * IllegalArgumentException 처리 (적절하지 않은 파라미터)
     *
     * @param e IllegalArgumentException
     * @return ResponseEntity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * DataIntegrityViolationException 처리 (DB 제약 조건 위반)
     *
     * @param e DataIntegrityViolationException
     * @return ResponseEntity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException occurred", e);
        return handleExceptionInternal(BAD_REQUEST);
    }

    /**
     * Exception 처리
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Unexpected Exception occurred", e);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(INTERNAL_SERVER_ERROR.getDescription());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponseBody(errorCode));
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponseBody((MethodArgumentNotValidException) e, errorCode));
    }

    private ErrorResponse makeErrorResponseBody(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getDescription())
                .build();
    }

    private ErrorResponse makeErrorResponseBody(BindException e, ErrorCode errorCode) {
        List<ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getDescription())
                .errors(validationErrorList)
                .build();
    }

}