package com.nextpagelabs.economiks.api.core.domain.entity;

import com.nextpagelabs.economiks.api.core.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "USER")
@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 30,  nullable = false)
    private String name;

    @Column(length = 20,  nullable = false)
    private String username;

    @Column(length = 72,  nullable = false)
    private String password;

    @Column(length = 8,  nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
