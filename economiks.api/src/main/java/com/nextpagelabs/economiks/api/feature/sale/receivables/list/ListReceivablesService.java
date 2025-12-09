package com.nextpagelabs.economiks.api.feature.sale.receivables.list;

import com.nextpagelabs.economiks.api.core.domain.entity.Payment;
import com.nextpagelabs.economiks.api.core.domain.entity.Sale;
import com.nextpagelabs.economiks.api.core.domain.enums.PaymentMethod;
import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;
import com.nextpagelabs.economiks.api.core.domain.repository.SaleRepository;
import com.nextpagelabs.economiks.api.feature.sale.receivables.ReceivableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListReceivablesService {

    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public List<ReceivableResponse> list() {
        List<Sale> pendingSales = saleRepository.findByStatus(SaleStatus.PENDING);

        return pendingSales.stream()
                .filter(this::hasCreditPayment)
                .map(ReceivableResponse::fromEntity)
                .toList();
    }

    private boolean hasCreditPayment(Sale sale) {
        return sale.getPayments().stream()
                .map(Payment::getMethod)
                .anyMatch(PaymentMethod::isCreditPayment);
    }
}

