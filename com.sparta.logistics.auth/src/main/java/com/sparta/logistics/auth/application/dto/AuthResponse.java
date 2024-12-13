package com.sparta.logistics.auth.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class AuthResponse {
    private String accessToken;

    public static AuthResponse of(String accessToken) {
        return AuthResponse.builder().accessToken(accessToken).build();
    }
}
