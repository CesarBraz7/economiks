package com.nextpagelabs.economiks.api.feature.product.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class RegisterProductController {

    private final RegisterProductService registerProductService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<RegisteredProductResponse> register(@Valid @RequestBody RegisterProductRequest request) {
        RegisteredProductResponse response = registerProductService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

