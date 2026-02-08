package com.nextpagelabs.economiks.api.feature.customer.list;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class ListCustomersController {

    private final ListCustomersService listCustomersService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'EMPLOYEE')")
    public ResponseEntity<List<CustomerResponse>> listCustomers(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(listCustomersService.findAll(name));
    }
}

