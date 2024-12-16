package com.sparta.logistics.auth.domain.model;

import lombok.Getter;

@Getter
public enum UserRole {
    MASTER_ADMIN("MASTER_ADMIN", "마스터 관리자"),
    HUB_ADMIN("HUB_ADMIN", "허브 관리자"),
    DELIVERY_USER("DELIVERY_USER", "배송 담당자"),
    COMPANY_USER("COMPANY_USER", "업체 담당자");

    private final String authority;
    private final String description;

    UserRole(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }

    public String getDescription() { return description; }

    public String getAuthority() { return authority; }

    private static class Authority {
        public static final String MASTER_ADMIN = "MASTER_ADMIN";
        public static final String HUB_ADMIN = "HUB_ADMIN";
        public static final String DELIVERY_USER = "DELIVERY_USER";
        public static final String COMPANY_USER = "COMPANY_USER";
    }
}
