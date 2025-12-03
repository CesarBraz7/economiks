package com.nextpagelabs.economiks.api.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum ProductStatus {
    ACTIVE,
    INACTIVE;

    @JsonCreator
    public static ProductStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(ProductStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: '" + value + "'. Accepted values are ACTIVE, INACTIVE."));
    }
}

