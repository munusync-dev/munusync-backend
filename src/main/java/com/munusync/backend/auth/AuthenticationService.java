package com.munusync.backend.auth;

import com.munusync.backend.config.JwtService;
import com.munusync.backend.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final OfficeRepository officeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var office = officeRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(office);

        return AuthenticateResponse.builder().token(jwtToken).build();
    }
}
