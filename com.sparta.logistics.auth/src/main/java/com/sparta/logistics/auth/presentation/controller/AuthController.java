package com.sparta.logistics.auth.presentation.controller;

import com.sparta.logistics.auth.application.dto.AuthResponse;
import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.application.service.AuthService;
import com.sparta.logistics.auth.application.service.UserService;
import com.sparta.logistics.auth.libs.exception.ErrorCode;
import com.sparta.logistics.auth.libs.model.ErrorResponse;
import com.sparta.logistics.auth.libs.model.ResponseMessage;
import com.sparta.logistics.auth.libs.model.SuccessResponse;
import com.sparta.logistics.auth.presentation.dto.CreateUserRequest;
import com.sparta.logistics.auth.presentation.dto.SignInRequest;
import com.sparta.logistics.auth.presentation.dto.ValidateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<SuccessResponse<AuthResponse>> signIn(@RequestBody @Valid SignInRequest request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.LOGIN_SUCCESS, authService.createAccessToken(request)));
    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<UserResponse>> signUp(@RequestBody @Valid CreateUserRequest request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.USER_CREATE_SUCCESS, userService.createUser(request.toDTO())));
    }

    // 필드 검증
    @GetMapping("/validation")
    public ResponseEntity<?> validateField(
            @RequestParam("field") String field,
            @RequestParam("value") String value) {

        Boolean valid;
        String message;

        switch (field.toLowerCase()) {
            case "username":
                valid = !userService.isUsernameExists(value);
                message = valid
                        ? ResponseMessage.VALID_USERNAME.getMessage()
                        : ErrorCode.USERNAME_ALEADY_EXISTS.getDescription();
                break;
            case "email":
                valid = !userService.isEmailExists(value);
                message = valid
                        ? ResponseMessage.VALID_EMAIL.getMessage()
                        : ErrorCode.EMAIL_ALEADY_EXISTS.getDescription();
                break;
            case "slackid":
                valid = userService.isSlackIdValid(value);
                message = valid
                        ? ResponseMessage.VALID_SLACKID.getMessage()
                        : ErrorCode.SLACKID_VALIDATION_FAILED.getDescription();
                break;
            default:
                // 잘못된 필드 요청은 에러 응답 반환
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .code(ErrorCode.INVALID_FIELD.name())
                        .message("Invalid field: " + field)
                        .build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // 성공 응답 생성
        ValidateResponse response = ValidateResponse.builder()
                .field(field)
                .valid(valid)
                .message(message)
                .build();

        return ResponseEntity.ok(SuccessResponse.of(ResponseMessage.FIELD_VALIDATION_SUCCESS, response));
    }

}
