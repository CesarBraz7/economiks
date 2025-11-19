package com.nextpagelabs.economiks.api.feature.customer.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Customer;
import com.nextpagelabs.economiks.api.core.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public RegisteredCustomerResponse register(RegisterCustomerRequest request) {

        Customer customer = Customer.create(
                request.name(),
                request.phone(),
                request.address()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return RegisteredCustomerResponse.fromEntity(savedCustomer);
    }
}
