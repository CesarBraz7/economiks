package com.nextpagelabs.economiks.api.core.domain.repository;

import com.nextpagelabs.economiks.api.core.domain.entity.Sale;
import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByStatus(SaleStatus status);

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            JOIN p.sale s
            WHERE s.saleDate >= :start
              AND s.saleDate < :end
              AND p.method <> 'CREDIT'
          """)
    BigDecimal sumCashPaymentsBySaleDateBetween(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            JOIN p.sale s
            WHERE p.paymentDate >= :start
              AND p.paymentDate < :end
              AND s.saleDate < :start
              AND p.method <> 'CREDIT'
          """)
    BigDecimal sumReceivablePaymentsByPaymentDateBetween(@Param("start") LocalDateTime start,
                                                         @Param("end") LocalDateTime end);

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Sale s
            JOIN s.payments p
            WHERE s.saleDate >= :start
              AND s.saleDate < :end
              AND p.method = 'CREDIT'
              AND s.status = 'PENDING'
          """)
    BigDecimal sumPendingCreditBySaleDateBetween(@Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end);

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Sale s
            JOIN s.payments p
            WHERE s.saleDate >= :start
              AND s.saleDate < :end
              AND p.method = 'CREDIT'
              AND s.status = 'PAID'
          """)
    BigDecimal sumReceivedCreditBySaleDateBetween(@Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);
}
