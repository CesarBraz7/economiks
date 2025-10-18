package com.nextpagelabs.economiks.api.feature.user.register;

import com.nextpagelabs.economiks.api.core.domain.entity.User;
import com.nextpagelabs.economiks.api.core.domain.enums.Role;

public record RegisteredUserResponse(
        Long id,
        String name,
        String username,
        Role role
) {

    public static RegisteredUserResponse fromEntity(User user) {
        return new RegisteredUserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getRole()
        );
    }
}
