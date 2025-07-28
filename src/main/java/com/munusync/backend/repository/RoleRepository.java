package com.munusync.backend.repository;

import com.munusync.backend.entity.Role;
import com.munusync.backend.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
