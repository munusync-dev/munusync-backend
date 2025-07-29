package com.munusync.backend.service;

import com.munusync.backend.dto.request.SignupRequest;
import com.munusync.backend.entity.Role;
import com.munusync.backend.entity.User;
import com.munusync.backend.enums.UserRole;
import com.munusync.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.munusync.backend.repository.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User createUser(SignupRequest request) {
        Role defaultRole = roleRepository.findByName(UserRole.CANDIDATE)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .role(defaultRole) // ✅ correct
                .build();

        return userRepository.save(user);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
