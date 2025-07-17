package com.munusync.backend.Dto.Request;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String role;

    private ProfileRequest profile;
}
