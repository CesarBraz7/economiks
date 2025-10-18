package com.nextpagelabs.economiks.api.core.domain.entity;

import com.nextpagelabs.economiks.api.core.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 8)
    private Role role;

    public static User create(
            String name,
            String username,
            String password,
            Role role,
            PasswordEncoder passwordEncoder
    ) {
        String encryptedPassword = passwordEncoder.encode(password);

        return new User(
                null,
                name,
                username,
                encryptedPassword,
                role
        );
    }
}
