package com.nextpagelabs.economiks.api.core.domain.repository;

import com.nextpagelabs.economiks.api.core.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
