package com.sparta.logistics.delivery.infrastructure.client.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationRequest {

  private String recipientId;  // slackId
  private String message;

}
