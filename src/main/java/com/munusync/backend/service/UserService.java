package com.munusync.backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.munusync.backend.dto.request.user.CreateUserRequest;
import com.munusync.backend.dto.request.user.UpdateUserRequest;
import com.munusync.backend.dto.response.user.UserResponse;
import com.munusync.backend.entity.User;
import com.munusync.backend.mapper.UserMapper;
import com.munusync.backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> getAllusers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper::toUserResponseDto);
    }

    public Optional<UserResponse> getUserById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toUserResponseDto);
    }
    

    public UserResponse createUser(CreateUserRequest request) {
        User user = UserMapper.toUser(request);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponseDto(savedUser);
    }

    public Optional<UserResponse> updateUser(UUID id, UpdateUserRequest request) {
        return userRepository.findById(id).map(existingUser -> {
            UserMapper.updateUserFromDto(existingUser, request);
            User updateUser = userRepository.save(existingUser);
            return UserMapper.toUserResponseDto(updateUser);
        });
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

}
