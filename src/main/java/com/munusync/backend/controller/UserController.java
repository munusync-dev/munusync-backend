package com.munusync.backend.controller;

import com.munusync.backend.dto.UserDTO;
import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Helper method: Convert User entity to UserDTO
    private UserDTO toDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }

    // Helper method: Convert UserDTO to User entity
    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(toEntity(userDTO));
        return toDTO(createdUser);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return toDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, toEntity(userDTO));
        return toDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
