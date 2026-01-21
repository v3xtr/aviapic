package com.api.backend.internal.intefaces.repo;

import com.api.backend.internal.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}