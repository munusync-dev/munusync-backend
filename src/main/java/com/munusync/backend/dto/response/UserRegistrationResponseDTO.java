package com.munusync.backend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponseDTO {
    private long id;
    private String userName;
    private String message;
}
