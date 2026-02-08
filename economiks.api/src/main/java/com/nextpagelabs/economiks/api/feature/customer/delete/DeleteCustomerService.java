package com.nextpagelabs.economiks.api.feature.customer.delete;

import com.nextpagelabs.economiks.api.core.domain.repository.CustomerRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found.");
        }
        // Integrity violation exceptions (e.g. customer has sales) will be handled by GlobalExceptionHandler
        customerRepository.deleteById(id);
    }
}

