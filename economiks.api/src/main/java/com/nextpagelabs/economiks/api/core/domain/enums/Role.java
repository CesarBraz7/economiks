package com.nextpagelabs.economiks.api.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Role {
    ADMIN,
    OWNER,
    EMPLOYEE;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Role.values())
                .filter(role -> role.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: '" + value + "'. Accepted values are ADMIN, OWNER, EMPLOYEE."));
    }
}
