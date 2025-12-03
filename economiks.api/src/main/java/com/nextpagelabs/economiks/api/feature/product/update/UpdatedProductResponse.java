package com.nextpagelabs.economiks.api.feature.product.update;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;

import java.math.BigDecimal;

public record UpdatedProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal retailPrice,
        BigDecimal wholesalePrice,
        ProductStatus status
) {
    public static UpdatedProductResponse fromEntity(Product product) {
        return new UpdatedProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRetailPrice(),
                product.getWholesalePrice(),
                product.getStatus()
        );
    }
}

