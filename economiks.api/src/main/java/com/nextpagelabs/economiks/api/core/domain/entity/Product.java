package com.nextpagelabs.economiks.api.core.domain.entity;

import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal retailPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal wholesalePrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ProductStatus status;

    public static Product create(
            String name,
            String description,
            BigDecimal retailPrice,
            BigDecimal wholesalePrice
    ) {
        return new Product(
                null,
                name,
                description,
                retailPrice,
                wholesalePrice,
                ProductStatus.ACTIVE
        );
    }

    public void inactivate() {
        this.status = ProductStatus.INACTIVE;
    }

    public void activate() {
        this.status = ProductStatus.ACTIVE;
    }

    public boolean isActive() {
        return this.status == ProductStatus.ACTIVE;
    }
}

