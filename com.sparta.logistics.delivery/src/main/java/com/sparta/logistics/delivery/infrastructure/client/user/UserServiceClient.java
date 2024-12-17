package com.sparta.logistics.delivery.infrastructure.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface UserServiceClient {

  @GetMapping("/users/{username}")
  ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username,
      @RequestHeader("X-Username") String h_username,
      @RequestHeader("X-Role") String role);
}
