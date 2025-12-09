package com.nextpagelabs.economiks.api.core.domain.entity;

import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sales")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal paidAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal remainingAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SaleStatus status;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public static Sale create(
            Customer customer,
            User user,
            LocalDateTime saleDate,
            BigDecimal totalAmount
    ) {
        Sale sale = new Sale();
        sale.customer = customer;
        sale.user = user;
        sale.saleDate = saleDate;
        sale.totalAmount = totalAmount;
        sale.paidAmount = BigDecimal.ZERO;
        sale.remainingAmount = totalAmount;
        sale.status = SaleStatus.PENDING;
        sale.createdAt = LocalDateTime.now();
        return sale;
    }

    public void addItem(SaleItem item) {
        items.add(item);
        item.setSale(this);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setSale(this);

        this.paidAmount = this.paidAmount.add(payment.getAmount());
        this.remainingAmount = this.totalAmount.subtract(this.paidAmount);

        if (this.remainingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            this.status = SaleStatus.COMPLETED;
        } else {
            this.status = SaleStatus.PENDING;
        }
    }

    public boolean requiresCustomer() {
        return payments.stream()
                .anyMatch(payment -> payment.getMethod().isCreditPayment());
    }

    public boolean isCompleted() {
        return this.status == SaleStatus.COMPLETED;
    }

    public boolean isPending() {
        return this.status == SaleStatus.PENDING;
    }
}

