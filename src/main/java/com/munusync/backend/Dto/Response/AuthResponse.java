package com.munusync.backend.Dto.Response;

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

    //adding using claude
    private String token;
    private String message;
}
