package com.nextpagelabs.economiks.api.feature.product.list;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;

import java.math.BigDecimal;

public record ProductListResponse(
        Long id,
        String name,
        String description,
        BigDecimal retailPrice,
        BigDecimal wholesalePrice,
        ProductStatus status
) {
    public static ProductListResponse fromEntity(Product product) {
        return new ProductListResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRetailPrice(),
                product.getWholesalePrice(),
                product.getStatus()
        );
    }
}

