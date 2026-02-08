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

        // vendas à vista (vendas do dia pagas com dinherio/pix/cartão)
        // inclui entradas de vendas a prazo feitas no dia
        BigDecimal totalCashSales = saleRepository.sumCashPaymentsBySaleDateBetween(startOfDay, endOfDay);

        // recebimentos de fiado (pagamentos hoje referentes a vendas passadas)
        BigDecimal totalReceivedFromCredit = saleRepository.sumReceivablePaymentsByPaymentDateBetween(startOfDay, endOfDay);

        // fiado pendente (vendas a prazo pendentes criadas hoje)
        BigDecimal totalCreditPending = saleRepository.sumPendingCreditBySaleDateBetween(startOfDay, endOfDay);

        // despesas do dia
        BigDecimal totalExpenses = expenseRepository.sumExpensesByDateBetween(startOfDay, endOfDay);

        // calculo do liquido: (vendas à vista + recebido de antigas) - despesas
        BigDecimal netBalance = totalCashSales
                .add(totalReceivedFromCredit)
                .subtract(totalExpenses);

        return new DailySummaryResponse(
                date,
                totalCashSales,
                totalCreditPending,
                totalReceivedFromCredit,
                totalExpenses,
                netBalance
        );
    }
}
