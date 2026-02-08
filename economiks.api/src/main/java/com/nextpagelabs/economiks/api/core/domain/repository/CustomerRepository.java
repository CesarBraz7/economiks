package com.nextpagelabs.economiks.api.core.domain.repository;

import com.nextpagelabs.economiks.api.core.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByNameContainingIgnoreCase(String name);
}
