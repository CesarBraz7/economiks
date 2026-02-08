package com.nextpagelabs.economiks.api.core.domain.repository;

import com.nextpagelabs.economiks.api.core.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByExpenseDateBetweenOrderByExpenseDateDesc(LocalDateTime start, LocalDateTime end);

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM Expense e
            WHERE e.expenseDate >= :start
              AND e.expenseDate < :end
            """)
    BigDecimal sumExpensesByDateBetween(@Param("start") LocalDateTime start,
                                        @Param("end") LocalDateTime end);
}
