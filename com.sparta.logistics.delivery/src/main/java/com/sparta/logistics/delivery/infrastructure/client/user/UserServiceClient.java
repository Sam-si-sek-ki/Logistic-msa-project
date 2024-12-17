package com.sparta.logistics.delivery.infrastructure.client.user;

import com.sparta.logistics.delivery.libs.model.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface UserServiceClient {

  @GetMapping("/users/{username}")
  ResponseEntity<SuccessResponse<UserResponse>> getUserByUsername(
      @PathVariable String username,
      @RequestHeader("X-Username") String h_username,
      @RequestHeader("X-Role") String role);
}
