package com.nextpagelabs.economiks.api.feature.expense.delete;

import com.nextpagelabs.economiks.api.core.domain.repository.ExpenseRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteExpenseService {

    private final ExpenseRepository expenseRepository;

    @Transactional
    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found.");
        }
        expenseRepository.deleteById(id);
    }
}

