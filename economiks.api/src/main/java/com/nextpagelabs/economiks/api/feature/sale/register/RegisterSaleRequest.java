package com.nextpagelabs.economiks.api.feature.sale.register;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record RegisterSaleRequest(

        Long customerId, // Opcional, mas obrigatório se houver pagamento CREDIT

        @NotNull(message = "Sale date is required.")
        LocalDateTime saleDate,

        @NotEmpty(message = "Sale must have at least one item.")
        @Valid
        List<SaleItemRequest> items,

        @NotEmpty(message = "Sale must have at least one payment.")
        @Valid
        List<PaymentRequest> payments
) { }

