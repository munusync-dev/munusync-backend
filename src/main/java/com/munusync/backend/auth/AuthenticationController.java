package com.munusync.backend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/auhtenticate")
    public ResponseEntity<AuthenticateResponse> auhtenticate(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
