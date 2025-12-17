package com.nextpagelabs.economiks.api.feature.expense.register;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisteredExpenseResponse(
        Long id,
        LocalDateTime expenseDate,
        BigDecimal amount,
        String category,
        String description
) {
}

