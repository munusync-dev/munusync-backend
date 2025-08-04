package com.munusync.backend.dto.response.user;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    
    private UUID id;

    private String firstName;
    
    private String lastName;
    
    private String email;
}
