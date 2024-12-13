package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.dto.AuthResponse;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import com.sparta.logistics.auth.presentation.dto.SignInRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    @Value("${spring.application.name}")
    private String issuer;
    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    public AuthService(UserRepository userRepository, @Value("${service.jwt.secret-key}") String secretKey, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.passwordEncoder = passwordEncoder;
    }

    // 로그인
    public AuthResponse createAccessToken(final SignInRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> AuthResponse.of(Jwts.builder()  // JWT 토큰을 생성합니다.
                        .claim("username", user.getUsername())
                        .claim("role", user.getRole())
                        .issuer(issuer)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact())
                ).orElseThrow();
    }
}
