package com.sparta.logistics.auth.domain.model;

import lombok.Getter;

@Getter
public enum UserRole {
    MASTER_ADMIN("마스터 관리자"),
    HUB_ADMIN("허브 관리자"),
    DELIVERY_USER("배송 담당자"),
    COMPANY_USER("업체 담당자");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
