package com.sparta.logistics.gateway.libs.filter;

import com.sparta.logistics.gateway.libs.config.AuthorizationRulesConfig;
import io.jsonwebtoken.Claims;
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
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthorizationFilter implements GlobalFilter, Ordered {

    private final AuthorizationRulesConfig authorizationRulesConfig;
    private final String secretKey;
    private final AntPathMatcher pathMatcher = new AntPathMatcher(); // AntPathMatcher 재사용

    public JwtAuthorizationFilter(AuthorizationRulesConfig authorizationRulesConfig,
            @Value("${service.jwt.secret-key}") String secretKey) {
        this.authorizationRulesConfig = authorizationRulesConfig;
        this.secretKey = secretKey;

        log.info("Loaded authorization rules: {}", authorizationRulesConfig);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod() != null ? exchange.getRequest().getMethod().name() : ""; // HTTP 메서드 추출
        String token = extractToken(exchange);

        // 검증 예외 처리
        if (pathMatcher.match("/auth/**", path) ||
                pathMatcher.match("/*/v3/api-docs", path) ||
                pathMatcher.match("/swagger-ui", path)) {
            return chain.filter(exchange);
        }

        if (token == null) {
            log.warn("Missing or invalid token for path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // JWT에서 클레임 추출
        Claims claims = extractClaimsFromToken(token);
        if (claims == null) {
            log.error("Failed to parse claims for token on path: {}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String username = claims.get("username", String.class);
        String userRole = claims.get("role", String.class);

        if (!isAuthorized(path, method, userRole)) {
            log.warn("Access denied for user: {} with role: {} on path: {}", username, userRole, path);
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // 요청 헤더에 클레임 추가
        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("X-Username", username != null ? username : "")
                .header("X-Role", userRole != null ? userRole : "")
                .build();
        log.info("Modified Headers: {}", modifiedRequest.getHeaders());

        ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();

        return chain.filter(modifiedExchange);
    }

    private boolean isAuthorized(String path, String method, String userRole) {
        List<AuthorizationRulesConfig.Rule> rules = authorizationRulesConfig.getRules();
        if (rules == null || rules.isEmpty()) {
            log.error("No authorization rules configured.");
            return false;
        }

        log.warn("=== isAuthorized: path: {} with method: {} on userRole: {}", path, method, userRole);
        for (AuthorizationRulesConfig.Rule rule : rules) {
            log.info("rule.getPath().: {}", rule.getPath());
            log.info("rule.getMethod().: {}", rule.getPath());
            log.info("rule.getRoles().: {}", rule.getPath());
            if (pathMatcher.match(rule.getPath(), path) &&
                    rule.getMethod().contains(method) &&
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

    private Claims extractClaimsFromToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Error parsing JWT: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int getOrder() {
        return -95; // JwtAuthorizationFilter는 JwtAuthenticationFilter 이후에 실행
    }
}
