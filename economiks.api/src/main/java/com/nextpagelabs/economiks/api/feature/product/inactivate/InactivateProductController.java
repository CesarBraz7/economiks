package com.nextpagelabs.economiks.api.feature.product.inactivate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class InactivateProductController {

    private final InactivateProductService inactivateProductService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        inactivateProductService.execute(id);
        return ResponseEntity.noContent().build();
    }
}

