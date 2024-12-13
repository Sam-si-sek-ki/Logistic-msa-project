package com.sparta.logistics.auth.presentation.controller;

import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.application.service.UserService;
import com.sparta.logistics.auth.libs.model.ResponseMessage;
import com.sparta.logistics.auth.libs.model.SuccessResponse;
import com.sparta.logistics.auth.presentation.dto.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.USER_CREATE_SUCCESS, userService.createUser(request.toDTO())));
    }

    // username 존재 여부 확인
    @GetMapping("/verify-username")
    public ResponseEntity<SuccessResponse<Boolean>> verifyUsername(final @RequestParam(value = "username") String username) {
        Boolean response = userService.verifyUsername(username);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }

    // email 존재 여부 확인
    @GetMapping("/verify-email")
    public ResponseEntity<SuccessResponse<Boolean>> verifyEmail(final @RequestParam(value = "email") String email) {
        Boolean response = userService.verifyEmail(email);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }


}
