package com.sparta.logistics.auth.presentation.dto;

import com.sparta.logistics.auth.application.dto.UserRequest;
import com.sparta.logistics.auth.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    @Size(max = 100, message = "nickname은 100자 이하여야 합니다.")
    private String nickname;

    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @Size(max = 255, message = "email은 255자 이하여야 합니다.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "password는 대문자, 소문자, 숫자, 특수문자를 포함한 8자 이상, 15자 이하여야 합니다."
    )
    private String password;

    private String slackId;

    private UserRole role;

    private UUID companyId;

    private UUID hubId;

    public UserRequest toDTO() {
        return UserRequest.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .slackId(slackId)
                .role(role)
                .companyId(companyId)
                .hubId(hubId)
                .build();
    }
}
