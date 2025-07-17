package com.munusync.backend.Service.Implentation;

import com.munusync.backend.Dto.Request.*;
import com.munusync.backend.Dto.Response.*;
import com.munusync.backend.Entities.*;
import com.munusync.backend.Repository.*;
import com.munusync.backend.Service.Interface.IAuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private UserRepository userRepository;

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthResponse.builder()
                    .message("Email already registered")
                    .build();
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        Profile profile = Profile.builder()
                .firstName(request.getProfile().getFirstName())
                .lastName(request.getProfile().getLastName())
                .bio(request.getProfile().getBio())
                .dateOfBirth(request.getProfile().getDateOfBirth())
                .created_at(LocalDateTime.now())
                .user(user)
                .build();

        user.setProfile(profile);
        User savedUser = userRepository.save(user);

        return AuthResponse.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .firstName(savedUser.getProfile().getFirstName())
                .lastName(savedUser.getProfile().getLastName())
                .message("Signup successful")
                .build();

    }


    public AuthResponse login(LoginRequest request) {


    }
}


