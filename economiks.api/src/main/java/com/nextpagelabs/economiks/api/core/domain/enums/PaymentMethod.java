package com.nextpagelabs.economiks.api.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum PaymentMethod {
    CASH,
    PIX,
    CARD,
    CREDIT; // fiado
    @JsonCreator
    public static PaymentMethod fromString(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(PaymentMethod.values())
                .filter(method -> method.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid payment method: '" + value + "'. Accepted values are CASH, PIX, CARD, CREDIT."));
    }

    public boolean isCreditPayment() {
        return this == CREDIT;
    }

    public boolean isInstantPayment() {
        return this == CASH || this == PIX || this == CARD;
    }
}

