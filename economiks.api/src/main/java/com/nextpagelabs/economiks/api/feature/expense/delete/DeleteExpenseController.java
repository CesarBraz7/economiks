package com.nextpagelabs.economiks.api.feature.expense.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class DeleteExpenseController {

    private final DeleteExpenseService deleteExpenseService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        deleteExpenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

