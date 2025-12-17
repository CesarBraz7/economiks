package com.nextpagelabs.economiks.api.feature.expense.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Expense;
import com.nextpagelabs.economiks.api.core.domain.entity.User;
import com.nextpagelabs.economiks.api.core.domain.repository.ExpenseRepository;
import com.nextpagelabs.economiks.api.core.domain.repository.UserRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public RegisteredExpenseResponse register(RegisterExpenseRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(request.amount());
        expense.setCategory(request.category());
        expense.setDescription(request.description());
        expense.setExpenseDate(request.expenseDate() != null ? request.expenseDate() : LocalDateTime.now());
        expense.setCreatedAt(LocalDateTime.now());

        Expense saved = expenseRepository.save(expense);

        return new RegisteredExpenseResponse(
                saved.getId(),
                saved.getExpenseDate(),
                saved.getAmount(),
                saved.getCategory(),
                saved.getDescription()
        );
    }
}

