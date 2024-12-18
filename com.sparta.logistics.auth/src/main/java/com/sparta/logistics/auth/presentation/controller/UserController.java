package com.sparta.logistics.auth.presentation.controller;

import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.application.service.UserService;
import com.sparta.logistics.auth.domain.model.UserRole;
import com.sparta.logistics.auth.libs.model.ResponseMessage;
import com.sparta.logistics.auth.libs.model.SuccessResponse;
import com.sparta.logistics.auth.presentation.dto.SignUpRequest;
import com.sparta.logistics.auth.presentation.dto.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users", description = "Users API")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 사용자 등록
    @PostMapping
    @Operation(summary = "사용자 등록", description = "사용자 등록 API")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(
        @RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok().body(SuccessResponse.of(ResponseMessage.USER_CREATE_SUCCESS,
            userService.createUser(request.toDTO())));
    }

    // 사용자 단건 조회
    @GetMapping("/{username}")
    @Operation(summary = "사용자 단건 조회", description = "사용자 단건 조회 API")
    public ResponseEntity<SuccessResponse<UserResponse>> getUserByUsername(
        @PathVariable String username,
        @RequestHeader(value = "X-Role", required = false) String requesterRole,
        @RequestHeader(value = "X-Username", required = false) String requesterUsername) {

        UserResponse userResponse = userService.getUserByUsername(username, requesterRole,
            requesterUsername);
        return ResponseEntity.ok()
            .body(SuccessResponse.of(ResponseMessage.USER_READ_SUCCESS, userResponse));
    }

    // 사용자 목록 조회
    @GetMapping
    @Operation(summary = "사용자 목록 조회", description = "사용자 목록 조회 API")
    @Parameters({
        @Parameter(name = "username", required = false, description = "username으로 검색"),
        @Parameter(name = "nickname", required = false, description = "nickname으로 검색"),
        @Parameter(name = "email", required = false, description = "email로 검색"),
        @Parameter(name = "role", required = false, description = "role로 검색"),
        @Parameter(name = "companyId", required = false, description = "companyId로 검색"),
        @Parameter(name = "hubId", required = false, description = "hubId로 검색"),
        @Parameter(name = "page", required = false, description = "현재 페이지 번호"),
        @Parameter(name = "size", required = false, description = "페이지 당 노출 건수"),
        @Parameter(name = "sortField", required = false, description = "정렬 기준 필드"),
        @Parameter(name = "sortDirection", required = false, description = "정렬 순서")
    })
    public ResponseEntity<SuccessResponse<Page<UserResponse>>> getUsers(
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String nickname,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) UserRole role,
        @RequestParam(required = false) String companyId,
        @RequestParam(required = false) String hubId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortField,
        @RequestParam(required = false) String sortDirection,
        @RequestHeader("X-Role") String requesterRole,
        @RequestHeader("X-Username") String requesterUsername) {
        Page<UserResponse> users = userService.getUsers(username, nickname, email, role, companyId,
            hubId,
            page, size, sortField, sortDirection, requesterRole, requesterUsername);
        return ResponseEntity.ok()
            .body(SuccessResponse.of(ResponseMessage.USER_READ_SUCCESS, users));
    }

    // 사용자 수정
    @PutMapping("/{username}")
    @Operation(summary = "사용자 수정", description = "사용자 수정 API")
    public ResponseEntity<SuccessResponse<UserResponse>> updateUser(
        @PathVariable String username,
        @RequestBody @Valid UserUpdateRequest request) {
        UserResponse updatedUser = userService.updateUser(username, request.toDTO());
        return ResponseEntity.ok()
            .body(SuccessResponse.of(ResponseMessage.USER_UPDATE_SUCCESS, updatedUser));
    }

    // 사용자 삭제
    @DeleteMapping("/{username}")
    @Operation(summary = "사용자 삭제", description = "사용자 삭제 API")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(
        @PathVariable String username,
        @RequestHeader("X-Username") String deletedBy) {
        userService.deleteUser(username, deletedBy);
        return ResponseEntity.ok()
            .body(SuccessResponse.of(ResponseMessage.USER_DELETE_SUCCESS, null));
    }

}
