package com.nextpagelabs.economiks.api.feature.customer.update;

import com.nextpagelabs.economiks.api.core.domain.entity.embeddable.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
        @NotBlank(message = "Name cannot be empty.")
        @Size(min = 3, max = 50, message = "Name length must be between 3 and 50 characters.")
        String name,

        @NotBlank(message = "Phone cannot be empty.")
        @Size(min = 11, max = 11, message = "Phone length must 11 characters.")
        String phone,

        @NotNull(message = "Address is required.")
        @Valid
        Address address
) {
}

