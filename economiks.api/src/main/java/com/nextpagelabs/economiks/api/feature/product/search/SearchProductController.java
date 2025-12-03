package com.nextpagelabs.economiks.api.feature.product.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class SearchProductController {

    private final SearchProductService searchProductService;

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<ProductSearchResponse>> searchProducts(
            @RequestParam String name
    ) {
        List<ProductSearchResponse> products = searchProductService.execute(name);
        return ResponseEntity.ok(products);
    }
}

