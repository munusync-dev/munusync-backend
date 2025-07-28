package com.munusync.backend.dto.response;

import com.munusync.backend.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private Long id;
    private String email;
    private String username;
    private String role;
    private String firstName;
    private String lastName;

    public static UserProfileResponse from(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole().getName().name())
                .firstName(user.getProfile().getFirstName())
                .lastName(user.getProfile().getLastName())
                .build();
    }
}
