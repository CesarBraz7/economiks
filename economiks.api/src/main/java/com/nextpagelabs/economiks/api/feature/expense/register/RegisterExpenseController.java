package com.nextpagelabs.economiks.api.feature.expense.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class RegisterExpenseController {

    private final RegisterExpenseService registerExpenseService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<RegisteredExpenseResponse> register(@RequestBody @Valid RegisterExpenseRequest request) {
        RegisteredExpenseResponse response = registerExpenseService.register(request);
        return ResponseEntity.ok(response);
    }
}
