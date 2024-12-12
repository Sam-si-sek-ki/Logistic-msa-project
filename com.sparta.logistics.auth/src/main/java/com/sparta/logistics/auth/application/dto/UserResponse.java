package com.sparta.logistics.auth.application.dto;

import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.model.UserRole;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String nickname;
    private String email;
    private String slackId;
    private UserRole role;
    private UUID companyId;
    private UUID hubId;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .slackId(user.getSlackId())
                .role(user.getRole())
                .companyId(user.getCompanyId())
                .hubId(user.getHubId())
                .build();
    }
}
