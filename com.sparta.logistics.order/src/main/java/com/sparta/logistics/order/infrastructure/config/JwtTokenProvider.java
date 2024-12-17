package com.sparta.logistics.order.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider {


    @Value("${service.jwt.secret-key}")
    private String secretKey;

    public boolean validateToken(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretKey);

            // 키 길이 로깅
            log.info("Secret Key Length: {} bytes", keyBytes.length);

            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token validation error", e);
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretKey);
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();

            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            // 로깅 추가
            log.info("Username from token: {}", username);
            log.info("Role from token: {}", role);

            List<GrantedAuthority> authorities =
                role != null
                    ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                    : Collections.emptyList();

            return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
            );
        } catch (Exception e) {
            log.error("Authentication creation error", e);
            throw e;
        }
    }
}