package com.munusync.backend.dto.request.user;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
}
