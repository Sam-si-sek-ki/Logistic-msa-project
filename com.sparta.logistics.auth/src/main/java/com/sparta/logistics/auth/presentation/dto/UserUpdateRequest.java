package com.sparta.logistics.auth.presentation.dto;

import com.sparta.logistics.auth.application.dto.UserRequest;
import com.sparta.logistics.auth.domain.model.UserRole;
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
    private String nickname;
    private String email;
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
