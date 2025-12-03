package com.nextpagelabs.economiks.api.feature.product.update;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<UpdatedProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        UpdatedProductResponse response = updateProductService.execute(id, request);
        return ResponseEntity.ok(response);
    }
}

