package com.nextpagelabs.economiks.api.feature.sale.receivables.register;

import java.math.BigDecimal;

public record RegisteredReceivablePaymentResponse(
        Long saleId,
        BigDecimal totalAmount,
        BigDecimal paidAmount,
        BigDecimal remainingAmount
) { }

