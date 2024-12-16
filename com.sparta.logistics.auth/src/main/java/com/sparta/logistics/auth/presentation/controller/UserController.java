package com.sparta.logistics.auth.presentation.controller;

import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.application.service.UserService;
import com.sparta.logistics.auth.libs.model.ResponseMessage;
import com.sparta.logistics.auth.libs.model.SuccessResponse;
import com.sparta.logistics.auth.presentation.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 사용자 등록
    @PostMapping
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.USER_CREATE_SUCCESS, userService.createUser(request.toDTO())));
    }

    // 사용자 단건 조회
    @GetMapping("/{username}")
    public ResponseEntity<SuccessResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.GET_USERS_SUCCESS, userService.getUserByUsername(username)));
    }

}
