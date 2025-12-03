package com.nextpagelabs.economiks.api.feature.product.list;

import com.nextpagelabs.economiks.api.core.domain.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ListProductController {

    private final ListProductService listProductService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<ProductListResponse>> listProducts(
            @RequestParam(required = false) ProductStatus status
    ) {
        List<ProductListResponse> products = listProductService.execute(status);
        return ResponseEntity.ok(products);
    }
}

