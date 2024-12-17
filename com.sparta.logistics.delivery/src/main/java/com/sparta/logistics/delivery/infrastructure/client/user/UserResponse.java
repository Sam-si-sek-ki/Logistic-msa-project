package com.sparta.logistics.delivery.infrastructure.client.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

  private String username;
  private String slackId;
}
