package com.nextpagelabs.economiks.api.feature.product.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Product;
import com.nextpagelabs.economiks.api.core.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterProductService {

    private final ProductRepository productRepository;

    @Transactional
    public RegisteredProductResponse execute(RegisterProductRequest request) {
        Product product = Product.create(
                request.name(),
                request.description(),
                request.retailPrice(),
                request.wholesalePrice()
        );

        Product savedProduct = productRepository.save(product);

        return RegisteredProductResponse.fromEntity(savedProduct);
    }
}

