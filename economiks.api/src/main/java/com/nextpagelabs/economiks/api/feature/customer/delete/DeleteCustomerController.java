package com.nextpagelabs.economiks.api.feature.customer.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class DeleteCustomerController {

    private final DeleteCustomerService deleteCustomerService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        deleteCustomerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

