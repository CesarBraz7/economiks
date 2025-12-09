package com.nextpagelabs.economiks.api.feature.sale.register;

import com.nextpagelabs.economiks.api.core.domain.entity.*;
import com.nextpagelabs.economiks.api.core.domain.repository.CustomerRepository;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import com.nextpagelabs.economiks.api.core.domain.repository.SaleRepository;
import com.nextpagelabs.economiks.api.core.domain.repository.UserRepository;
import com.nextpagelabs.economiks.api.core.exception.ForbiddenOperationException;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RegisterSaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Transactional
    public RegisteredSaleResponse execute(RegisterSaleRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        boolean hasCreditPayment = request.payments().stream()
                .anyMatch(p -> p.method().isCreditPayment());

        Customer customer = null;
        if (request.customerId() != null) {
            customer = customerRepository.findById(request.customerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.customerId()));
        }

        if (hasCreditPayment && customer == null) {
            throw new ForbiddenOperationException("Credit payment requires a customer.");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;

        Sale sale = Sale.create(customer, user, request.saleDate(), totalAmount);

        for (SaleItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemRequest.productId()));

            if (!product.isActive()) {
                throw new ForbiddenOperationException("Product is inactive: " + product.getName());
            }

            SaleItem item = SaleItem.create(
                    product,
                    itemRequest.quantity(),
                    itemRequest.unitPrice()
            );

            sale.addItem(item);
            totalAmount = totalAmount.add(item.getSubtotal());
        }

        sale.setTotalAmount(totalAmount);
        sale.setRemainingAmount(totalAmount);

        BigDecimal totalPayments = request.payments().stream()
                .map(PaymentRequest::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPayments.compareTo(totalAmount) > 0) {
            throw new ForbiddenOperationException("Total payments cannot exceed sale total.");
        }

        for (PaymentRequest paymentRequest : request.payments()) {
            Payment payment = Payment.create(
                    paymentRequest.method(),
                    paymentRequest.amount(),
                    request.saleDate()
            );
            sale.addPayment(payment);
        }

        Sale savedSale = saleRepository.save(sale);

        return new RegisteredSaleResponse(
                savedSale.getId(),
                savedSale.getCustomer() != null ? savedSale.getCustomer().getId() : null,
                savedSale.getCustomer() != null ? savedSale.getCustomer().getName() : null,
                savedSale.getSaleDate(),
                savedSale.getTotalAmount(),
                savedSale.getPaidAmount(),
                savedSale.getRemainingAmount(),
                savedSale.getStatus(),
                savedSale.getItems().size(),
                savedSale.getPayments().size()
        );
    }
}
