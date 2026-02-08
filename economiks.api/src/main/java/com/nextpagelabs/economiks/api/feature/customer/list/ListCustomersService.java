package com.nextpagelabs.economiks.api.feature.customer.list;

import com.nextpagelabs.economiks.api.core.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCustomersService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll(String name) {
        var customers = (name != null && !name.isBlank())
                ? customerRepository.findAllByNameContainingIgnoreCase(name)
                : customerRepository.findAll();

        return customers.stream()
                .map(CustomerResponse::from)
                .sorted(Comparator.comparing(CustomerResponse::name))
                .toList();
    }
}

