package com.nextpagelabs.economiks.api.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum SaleStatus {
    COMPLETED,
    PENDING,
    CANCELLED;

    @JsonCreator
    public static SaleStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(SaleStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid sale status: '" + value + "'. Accepted values are COMPLETED, PENDING, CANCELLED."));
    }
}

