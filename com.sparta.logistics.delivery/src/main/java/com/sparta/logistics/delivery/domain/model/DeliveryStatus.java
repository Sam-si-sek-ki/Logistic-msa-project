package com.sparta.logistics.delivery.domain.model;

public enum DeliveryStatus {

  PENDING,
  HUB_WAITING,
  HUB_MOVING,
  HUB_ARRIVED,
  IN_DELIVERY,
  DELIVERED,
  FAILED,
  CANCELED
}
