package com.munusync.backend.service;

import com.munusync.backend.dto.request.LoginRequest;
import com.munusync.backend.dto.request.SignupRequest;
import com.munusync.backend.dto.request.TokenRefreshRequest;
import com.munusync.backend.dto.response.AuthResponse;
import com.munusync.backend.entity.Profile;
import com.munusync.backend.entity.User;
import com.munusync.backend.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final ProfileService profileService;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse signup(SignupRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            return AuthResponse.builder()
                    .message("Email already registered")
                    .build();
        }

        User user = userService.createUser(request);
        Profile profile = profileService.createProfile(user, request.getProfile());
        user.setProfile(profile);

        // Generate JWT token after successful signup
        String token = jwtTokenProvider.createToken(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null,
                        List.of(new SimpleGrantedAuthority(user.getRole().getName().name()
))
                )
        );

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName().name()
)
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .token(token)
                .message("Signup successful")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userService.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.createToken(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null,
                        List.of(new SimpleGrantedAuthority(user.getRole().getName().name()
))
                )
        );

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName().name()
)
                .firstName(user.getProfile().getFirstName())
                .lastName(user.getProfile().getLastName())
                .token(token)
                .message("Login successful")
                .build();
    }

    public AuthResponse refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Authentication auth = jwtTokenProvider.getAuthentication(refreshToken);
        String newAccessToken = jwtTokenProvider.createToken(auth);

        User user = userService.findByEmail(auth.getName());

        return AuthResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().getName().name())
                .firstName(user.getProfile().getFirstName())
                .lastName(user.getProfile().getLastName())
                .token(newAccessToken)
                .message("Access token refreshed successfully")
                .build();
    }

}
