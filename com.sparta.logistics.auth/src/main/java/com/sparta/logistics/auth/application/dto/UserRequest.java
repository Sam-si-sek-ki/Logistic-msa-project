package com.sparta.logistics.auth.application.dto;

import com.sparta.logistics.auth.domain.model.UserRole;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserRequest {

    private String username;
    private String nickname;
    private String email;
    private String password;
    private String slackId; // TODO : slackId 유효성 검사
    private UserRole role;
    private UUID companyId;
    private UUID hubId;

    public static UserRequest create(String username, String nickname, String email, String password, String slackId, UserRole role, UUID companyId, UUID hubId) {
        return UserRequest.builder()
                .username(username)
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
