package com.nextpagelabs.economiks.api.core.domain.entity;

import com.nextpagelabs.economiks.api.core.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "payments")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod method;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    public static Payment create(
            PaymentMethod method,
            BigDecimal amount,
            LocalDateTime paymentDate
    ) {
        Payment payment = new Payment();
        payment.method = method;
        payment.amount = amount;
        payment.paymentDate = paymentDate;
        return payment;
    }
}


