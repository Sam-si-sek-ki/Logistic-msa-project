package com.sparta.logistics.delivery.infrastructure.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

  @PostMapping("/notifications/slack/send")
  public void sendNotification(@RequestBody NotificationRequest request);
}
