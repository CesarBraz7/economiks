package com.nextpagelabs.economiks.api.feature.dashboard.daily_summary;

import com.nextpagelabs.economiks.api.core.domain.repository.ExpenseRepository;
import com.nextpagelabs.economiks.api.core.domain.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DailySummaryService {

    private final SaleRepository saleRepository;
    private final ExpenseRepository expenseRepository;

    public DailySummaryResponse getDailySummary(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        BigDecimal totalCashSales = saleRepository.sumCashPaymentsBySaleDateBetween(startOfDay, endOfDay);
        BigDecimal totalCreditReceivables = saleRepository.sumPendingCreditBySaleDateBetween(startOfDay, endOfDay);
        BigDecimal totalReceivedCredit = saleRepository.sumReceivedCreditBySaleDateBetween(startOfDay, endOfDay);

        BigDecimal totalExpenses = expenseRepository.sumExpensesByDateBetween(startOfDay, endOfDay);

        BigDecimal netBalance = totalCashSales
                .add(totalReceivedCredit)
                .subtract(totalExpenses);

        return new DailySummaryResponse(
                date,
                totalCashSales,
                totalCreditReceivables,
                totalExpenses,
                netBalance
        );
    }
}
