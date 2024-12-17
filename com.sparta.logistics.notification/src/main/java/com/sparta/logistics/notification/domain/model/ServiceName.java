package com.sparta.logistics.notification.domain.model;

import lombok.Getter;

@Getter
public enum ServiceName {
    AUTH("auth-service"),
    COMPANY("company-service"),
    DELIVERY("delivery-service"),
    HUB("hub-service"),
    NOTIFICATION("notification-service"),
    ORDER("order-service"),
    PRODUCT("product-service");

    private final String description;

    ServiceName(String description) {
        this.description = description;
    }
}
