package com.nextpagelabs.economiks.api.feature.dashboard.daily_summary;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailySummaryResponse(
        LocalDate date,
        BigDecimal totalCashSales,
        BigDecimal totalCreditReceivables,
        BigDecimal totalReceivedFromCredit,
        BigDecimal totalExpenses,
        BigDecimal netBalance
) {
}
