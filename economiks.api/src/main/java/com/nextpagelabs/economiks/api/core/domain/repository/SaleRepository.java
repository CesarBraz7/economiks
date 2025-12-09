package com.nextpagelabs.economiks.api.core.domain.repository;

import com.nextpagelabs.economiks.api.core.domain.entity.Sale;
import com.nextpagelabs.economiks.api.core.domain.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByStatus(SaleStatus status);
}
