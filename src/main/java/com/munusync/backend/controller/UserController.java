package com.munusync.backend.controller;

<<<<<<< HEAD
import com.munusync.backend.dto.UserDTO;
import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
=======
import com.munusync.backend.dtos.UserDto;
import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> 85852505ceee28e61442811cd688f849a908978b

@RestController
@RequestMapping("/users")
public class UserController {
<<<<<<< HEAD

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
=======
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users =  userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        ResponseEntity<UserDto> user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return user;
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User user = userService.updateUser(id, updateUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        ResponseEntity<UserDto> user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

>>>>>>> 85852505ceee28e61442811cd688f849a908978b
}
