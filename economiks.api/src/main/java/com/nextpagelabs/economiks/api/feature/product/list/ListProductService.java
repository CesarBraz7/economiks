package com.nextpagelabs.economiks.api.feature.product.list;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponse> execute(ProductStatus status) {
        List<Product> products;

        if (status != null) {
            products = productRepository.findByStatus(status);
        } else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(ProductListResponse::fromEntity)
                .toList();
    }
}
