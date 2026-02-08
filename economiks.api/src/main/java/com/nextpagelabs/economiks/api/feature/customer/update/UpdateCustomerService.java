package com.nextpagelabs.economiks.api.feature.customer.update;

import com.nextpagelabs.economiks.api.core.domain.repository.CustomerRepository;
import com.nextpagelabs.economiks.api.core.domain.entity.Customer;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import com.nextpagelabs.economiks.api.feature.customer.list.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponse update(Long id, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));

        customer.setName(request.name());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        customerRepository.save(customer);

        return CustomerResponse.from(customer);
    }
}

