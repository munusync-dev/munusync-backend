package com.munusync.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private Long userId;
    private String email;
    private String role;

    private String firstName;
    private String lastName;

    private String token;
    private String message;
}
