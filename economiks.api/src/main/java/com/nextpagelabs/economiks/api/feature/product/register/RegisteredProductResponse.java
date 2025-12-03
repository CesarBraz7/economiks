package com.nextpagelabs.economiks.api.feature.product.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;

import java.math.BigDecimal;

public record RegisteredProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal retailPrice,
        BigDecimal wholesalePrice,
        ProductStatus status
) {
    public static RegisteredProductResponse fromEntity(Product product) {
        return new RegisteredProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getRetailPrice(),
                product.getWholesalePrice(),
                product.getStatus()
        );
    }
}

