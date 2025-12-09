package com.nextpagelabs.economiks.api.feature.sale.receivables.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Payment;
import com.nextpagelabs.economiks.api.core.domain.entity.Sale;
import com.nextpagelabs.economiks.api.core.domain.enums.PaymentMethod;
import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;
import com.nextpagelabs.economiks.api.core.domain.repository.SaleRepository;
import com.nextpagelabs.economiks.api.core.exception.ForbiddenOperationException;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterReceivablePaymentService {

    private final SaleRepository saleRepository;

    @Transactional
    public RegisteredReceivablePaymentResponse register(Long saleId, RegisterReceivablePaymentRequest request) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + saleId));

        if (!SaleStatus.PENDING.equals(sale.getStatus())) {
            throw new ForbiddenOperationException("Only pending sales can receive receivable payments.");
        }

        boolean hasCreditPayment = sale.getPayments().stream()
                .map(Payment::getMethod)
                .anyMatch(PaymentMethod::isCreditPayment);

        if (!hasCreditPayment) {
            throw new ForbiddenOperationException("Sale does not have credit payments.");
        }

        BigDecimal amount = request.amount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ForbiddenOperationException("Amount must be greater than zero.");
        }

        if (amount.compareTo(sale.getRemainingAmount()) > 0) {
            throw new ForbiddenOperationException("Amount cannot be greater than remaining amount.");
        }

        Payment payment = Payment.create(
                request.method(),
                request.amount(),
                LocalDateTime.now()
        );

        sale.addPayment(payment);

        Sale savedSale = saleRepository.save(sale);

        return new RegisteredReceivablePaymentResponse(
                savedSale.getId(),
                savedSale.getTotalAmount(),
                savedSale.getPaidAmount(),
                savedSale.getRemainingAmount()
        );
    }
}

