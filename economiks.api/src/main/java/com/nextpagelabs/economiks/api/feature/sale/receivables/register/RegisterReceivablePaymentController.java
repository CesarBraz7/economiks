package com.nextpagelabs.economiks.api.feature.sale.receivables.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale/{saleId}/receivables/payments")
@RequiredArgsConstructor
public class RegisterReceivablePaymentController {

    private final RegisterReceivablePaymentService registerReceivablePaymentService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<RegisteredReceivablePaymentResponse> register(
            @PathVariable Long saleId,
            @Valid @RequestBody RegisterReceivablePaymentRequest request
    ) {
        RegisteredReceivablePaymentResponse response = registerReceivablePaymentService.register(saleId, request);
        return ResponseEntity.ok(response);
    }
}

