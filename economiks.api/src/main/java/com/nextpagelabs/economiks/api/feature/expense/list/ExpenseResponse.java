package com.nextpagelabs.economiks.api.feature.expense.list;

import com.nextpagelabs.economiks.api.core.domain.entity.Expense;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseResponse(
        Long id,
        LocalDateTime expenseDate,
        BigDecimal amount,
        String category,
        String description,
        String userName
) {
    public static ExpenseResponse from(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getExpenseDate(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDescription(),
                expense.getUser().getName()
        );
    }
}

