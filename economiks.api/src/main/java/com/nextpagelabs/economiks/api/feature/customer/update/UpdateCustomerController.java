package com.nextpagelabs.economiks.api.feature.customer.update;

import com.nextpagelabs.economiks.api.feature.customer.list.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class UpdateCustomerController {

    private final UpdateCustomerService updateCustomerService;

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCustomerRequest request
    ) {
        return ResponseEntity.ok(updateCustomerService.update(id, request));
    }
}

