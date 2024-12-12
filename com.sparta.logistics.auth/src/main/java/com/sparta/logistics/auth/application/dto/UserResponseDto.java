package com.sparta.logistics.auth.application.dto;

import com.sparta.logistics.auth.domain.model.UserRole;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private String nickname;
    private String email;
    private String slackId;
    private UserRole role;
    private UUID companyId;
    private UUID hubId;
}
