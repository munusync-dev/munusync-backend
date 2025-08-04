package com.munusync.backend.mapper;

import com.munusync.backend.dto.request.user.CreateUserRequest;
import com.munusync.backend.dto.request.user.UpdateUserRequest;
import com.munusync.backend.dto.response.user.UserResponse;
import com.munusync.backend.entity.User;

public class UserMapper {

    public static UserResponse toUserResponseDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(CreateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    public static void updateUserFromDto(User user, UpdateUserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
    }

}
