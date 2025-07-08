package com.munusync.backend.dto.request;

import lombok.*;

@Builder
@Getter
public final class createUserRequestDTO {
    String name;
    String email;
}