package com.munusync.backend.dto.response;

import java.util.UUID;

import lombok.*;

@Builder
@Getter
public final class CreateUserResponse {
    UUID id;
    String name;
    String email;
}