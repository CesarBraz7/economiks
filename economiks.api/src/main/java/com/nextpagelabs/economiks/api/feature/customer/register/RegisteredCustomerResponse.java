package com.nextpagelabs.economiks.api.feature.customer.register;

import com.nextpagelabs.economiks.api.core.domain.entity.Customer;
import com.nextpagelabs.economiks.api.core.domain.entity.embeddable.Address;

public record RegisteredCustomerResponse(
        Long id,
        String name,
        String phone,
        Address address
) {
    public static RegisteredCustomerResponse fromEntity(Customer customer) {
        return new RegisteredCustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}
