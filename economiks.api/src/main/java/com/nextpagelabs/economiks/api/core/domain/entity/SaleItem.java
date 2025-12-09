package com.nextpagelabs.economiks.api.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "sale_items")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public static SaleItem create(
            Product product,
            Integer quantity,
            BigDecimal unitPrice
    ) {
        SaleItem item = new SaleItem();
        item.product = product;
        item.quantity = quantity;
        item.unitPrice = unitPrice;
        item.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return item;
    }
}

