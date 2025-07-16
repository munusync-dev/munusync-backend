package com.munusync.backend.mapper;

import com.munusync.backend.dto.request.user.CreateUserRequest;
import com.munusync.backend.dto.request.user.UpdateUserRequest;
import com.munusync.backend.dto.response.user.UserResponse;
import com.munusync.backend.entity.User;

public class UserMapper {

    public static UserResponse toUserResponseDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(CreateUserRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .build();
    }

    public static void updateUserFromDto(User user, UpdateUserRequest request) {
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
    }

}
