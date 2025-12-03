package com.nextpagelabs.economiks.api.feature.product.search;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductSearchResponse> execute(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(ProductSearchResponse::fromEntity)
                .toList();
    }
}

