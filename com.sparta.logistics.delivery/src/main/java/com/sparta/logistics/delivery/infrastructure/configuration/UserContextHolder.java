package com.sparta.logistics.delivery.infrastructure.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContextHolder {

  private final HttpServletRequest httpServletRequest;
  private static final String USER_HEADER = "X-Username";

  public String getCurrentAuditor() {
    String username = httpServletRequest.getHeader(USER_HEADER);
    return username != null ? username : "SYSTEM"; // 헤더 없을 경우 default
  }
}
