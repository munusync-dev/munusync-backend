package com.munusync.backend.dto.request.user;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String firstName;
    
    private String lastName;

    private String email;

}
