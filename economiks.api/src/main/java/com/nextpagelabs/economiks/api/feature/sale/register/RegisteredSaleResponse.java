package com.nextpagelabs.economiks.api.feature.sale.register;

import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisteredSaleResponse(
        Long id,
        Long customerId,
        String customerName,
        LocalDateTime saleDate,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount,
        SaleStatus status,
        Integer itemsCount,
        Integer paymentsCount
) { }

