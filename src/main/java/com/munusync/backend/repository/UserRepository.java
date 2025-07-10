package com.munusync.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munusync.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existByEmail(String email);
}
