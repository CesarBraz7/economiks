package com.nextpagelabs.economiks.api.feature.sale.receivables;

import com.nextpagelabs.economiks.api.core.domain.entity.Sale;
import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReceivableResponse(
        Long saleId,
        Long customerId,
        String customerName,
        LocalDateTime saleDate,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount,
        SaleStatus status
) {
    public static ReceivableResponse fromEntity(Sale sale) {
        return new ReceivableResponse(
                sale.getId(),
                sale.getCustomer() != null ? sale.getCustomer().getId() : null,
                sale.getCustomer() != null ? sale.getCustomer().getName() : null,
                sale.getSaleDate(),
                sale.getTotalAmount(),
                sale.getPaidAmount(),
                sale.getRemainingAmount(),
                sale.getStatus()
        );
    }
}

