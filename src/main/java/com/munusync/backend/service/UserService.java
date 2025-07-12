package com.munusync.backend.service;

import com.munusync.backend.Dto.UserRequest;
import com.munusync.backend.Dto.UserResponse;
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
    
        User savedUser = userRepository.save(user);
    
        return UserResponse.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
    
    

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    
        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
    
        User updatedUser = userRepository.save(existingUser);
    
        return UserResponse.builder()
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .build();
    }
    

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}