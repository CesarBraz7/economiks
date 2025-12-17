package com.nextpagelabs.economiks.api.feature.expense.register;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisterExpenseRequest(
        @NotNull @DecimalMin("0.01") BigDecimal amount,
        @NotBlank String category,
        String description,
        LocalDateTime expenseDate
) {
}

