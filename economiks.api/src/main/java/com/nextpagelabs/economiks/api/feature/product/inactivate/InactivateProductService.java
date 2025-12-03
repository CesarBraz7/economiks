package com.nextpagelabs.economiks.api.feature.product.inactivate;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InactivateProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void execute(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        product.inactivate();
        productRepository.save(product);
    }
}

