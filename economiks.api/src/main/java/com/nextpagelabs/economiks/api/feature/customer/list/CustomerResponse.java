package com.nextpagelabs.economiks.api.feature.customer.list;

import com.nextpagelabs.economiks.api.core.domain.entity.Customer;
import com.nextpagelabs.economiks.api.core.domain.entity.embeddable.Address;

public record CustomerResponse(
        Long id,
        String name,
        String phone,
        Address address
) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}

