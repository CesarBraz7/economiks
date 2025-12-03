package com.nextpagelabs.economiks.api.feature.product.search;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;

import java.math.BigDecimal;

public record ProductSearchResponse(
        Long id,
        String name,
        String description,
        BigDecimal retailPrice,
        BigDecimal wholesalePrice,
        ProductStatus status
) {
    public static ProductSearchResponse fromEntity(Product product) {
        return new ProductSearchResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRetailPrice(),
                product.getWholesalePrice(),
                product.getStatus()
        );
    }
}

