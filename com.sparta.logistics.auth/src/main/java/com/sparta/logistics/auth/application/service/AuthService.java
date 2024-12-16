package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.dto.AuthResponse;
import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import com.sparta.logistics.auth.libs.exception.ErrorCode;
import com.sparta.logistics.auth.libs.exception.GlobalException;
import com.sparta.logistics.auth.libs.security.JwtUtil;
import com.sparta.logistics.auth.presentation.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 로그인
    public AuthResponse login(final SignInRequest request) {
        // 사용자 확인
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND)
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GlobalException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        // JWT 토큰 생성 및 반환
        String token = jwtUtil.createAccessToken(user.getUsername(), user.getRole());

        return AuthResponse.of(token);
    }

}
