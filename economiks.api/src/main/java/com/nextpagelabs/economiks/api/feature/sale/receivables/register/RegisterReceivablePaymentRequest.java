package com.nextpagelabs.economiks.api.feature.sale.receivables.register;

import com.nextpagelabs.economiks.api.core.domain.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegisterReceivablePaymentRequest(

        @NotNull(message = "Payment method is required.")
        PaymentMethod method,

        @NotNull(message = "Amount is required.")
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero.")
        BigDecimal amount
) { }

