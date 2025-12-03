package com.nextpagelabs.economiks.api.feature.product.register;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegisterProductRequest(
        @NotBlank(message = "Product name is required")
        String name,

        String description,

        @NotNull(message = "Retail price is required")
        @DecimalMin(value = "0.01", message = "Retail price must be greater than zero")
        BigDecimal retailPrice,

        @NotNull(message = "Wholesale price is required")
        @DecimalMin(value = "0.01", message = "Wholesale price must be greater than zero")
        BigDecimal wholesalePrice
) {
}

