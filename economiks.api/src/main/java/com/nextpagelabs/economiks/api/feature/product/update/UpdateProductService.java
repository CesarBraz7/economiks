package com.nextpagelabs.economiks.api.feature.product.update;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProductService {

    private final ProductRepository productRepository;

    @Transactional
    public UpdatedProductResponse execute(Long productId, UpdateProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setRetailPrice(request.retailPrice());
        product.setWholesalePrice(request.wholesalePrice());

        Product updatedProduct = productRepository.save(product);

        return UpdatedProductResponse.fromEntity(updatedProduct);
    }
}

