package com.munusync.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munusync.backend.Repository.UserRepository;
import com.munusync.backend.dto.request.user.CreateUserRequest;
import com.munusync.backend.dto.request.user.UpdateUserRequest;
import com.munusync.backend.dto.response.user.UserResponse;
import com.munusync.backend.entity.User;
import com.munusync.backend.mapper.UserMapper;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllusers() {
        return userRepository.findAll().stream().map(UserMapper::toUserResponseDto).collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserResponseDto);
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = UserMapper.toUser(request);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponseDto(savedUser);
    }

    public Optional<UserResponse> updateUser(Long id, UpdateUserRequest request) {
        return userRepository.findById(id).map(existingUser -> {
            UserMapper.updateUserFromDto(existingUser, request);
            User updateUser = userRepository.save(existingUser);
            return UserMapper.toUserResponseDto(updateUser);
        });
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

}
