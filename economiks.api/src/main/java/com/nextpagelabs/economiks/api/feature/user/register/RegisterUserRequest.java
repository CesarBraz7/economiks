package com.nextpagelabs.economiks.api.feature.user.register;

import com.nextpagelabs.economiks.api.core.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Name cannot be empty.")
        @Size(min = 3, max = 50, message = "Name length must be between 3 and 50 characters.")
        String name,

        @NotBlank(message = "Username cannot be empty.")
        @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters.")
        String username,

        @NotBlank(message = "Password is required.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must be at least 8 characters, including at least one uppercase letter, one lowercase, " +
                        "one number and one special character"
        )
        String password,

        @NotNull(message = "A role needs to be informed.")
        Role role
) { }
