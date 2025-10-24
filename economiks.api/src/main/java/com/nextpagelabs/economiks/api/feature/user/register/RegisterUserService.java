package com.nextpagelabs.economiks.api.feature.user.register;

import com.nextpagelabs.economiks.api.core.domain.entity.User;
import com.nextpagelabs.economiks.api.core.domain.repository.UserRepository;
import com.nextpagelabs.economiks.api.core.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisteredUserResponse register(RegisterUserRequest request) {

        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new ResourceAlreadyExistsException(
                    "the username '%s' is already taken".formatted(request.username())
            );
        });

        User user = User.create(
                request.name(),
                request.username(),
                request.password(),
                request.role(),
                passwordEncoder
        );

        User savedUser = userRepository.save(user);

        return RegisteredUserResponse.fromEntity(savedUser);
    }
}
