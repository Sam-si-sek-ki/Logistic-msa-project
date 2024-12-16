package com.sparta.logistics.gateway.libs.filter;

import com.sparta.logistics.gateway.libs.config.AuthorizationRulesConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthorizationFilter implements GlobalFilter, Ordered {

    private final AuthorizationRulesConfig authorizationRulesConfig;
    private final String secretKey;

    public JwtAuthorizationFilter(AuthorizationRulesConfig authorizationRulesConfig,
            @Value("${service.jwt.secret-key}") String secretKey) {
        this.authorizationRulesConfig = authorizationRulesConfig;
        this.secretKey = secretKey;

        log.info("authorizationRulesConfig : " + authorizationRulesConfig.toString());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name(); // HTTP 메서드 추출
        String token = extractToken(exchange);

        // /auth 로 시작하는 요청들은 검증하지 않습니다.
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        if (token == null || !validateToken(token)) {
            log.info("invalid token : " + token);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String userRole = extractRoleFromToken(token);
        if (!isAuthorized(path, method, userRole)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private boolean isAuthorized(String path, String method, String userRole) {
        log.info("path : " + path);
        log.info("method : " + method);
        log.info("userRole : " + userRole);

        List<AuthorizationRulesConfig.Rule> rules = authorizationRulesConfig.getRules();
        if (rules == null || rules.isEmpty()) {
            log.error("Authorization rules are not configured.");
            return false;
        }

        AntPathMatcher pathMatcher = new AntPathMatcher(); // AntPathMatcher 인스턴스 생성

        for (AuthorizationRulesConfig.Rule rule : rules) {
            if (pathMatcher.match(rule.getPath(), path) && // 경로 매칭
                    rule.getMethod().contains(method) && // 다중 메서드 확인
                    rule.getRoles().contains(userRole)) {
                return true;
            }
        }
        return false;
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }

    private String extractRoleFromToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody().get("role", String.class);
        } catch (Exception e) {
            log.error("Failed to extract role from JWT: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int getOrder() {
        return -90; // JwtAuthorizationFilter는 JwtAuthenticationFilter 이후에 실행
    }
}
