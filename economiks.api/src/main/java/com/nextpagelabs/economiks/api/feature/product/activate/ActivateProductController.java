package com.nextpagelabs.economiks.api.feature.product.activate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ActivateProductController {

    private final ActivateProductService activateProductService;

    @PatchMapping("/{id}/activate")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        activateProductService.execute(id);
        return ResponseEntity.noContent().build();
    }
}

