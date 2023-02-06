package com.project.domain.constant;

import lombok.Getter;

public enum UserRole {
    VENDOR("ROLE_VENDOR"), CONSUMER("ROLE_CONSUMER"),ADMIN("ROLE_ADMIN");

    @Getter
    private String value;

    UserRole(String value) {
        this.value = value;
    }
}
