package com.munusync.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.munusync.backend.dto.request.CreateUserRequest;
import com.munusync.backend.dto.response.CreateUserResponse;
import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /users → create user.
    // GET /users → get all users.
    // GET /users/{id} → get user by id.
    // PUT /users/{id} → update user.
    // DELETE /users/{id} → delete user.

    // post
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest user) {

        if (userService.isEmailInUse(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email aleady exist");
        }

        CreateUserResponse savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // get
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersList = userService.getAllUsers();
        if (usersList.size() == 0) {
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {

        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {

        if (id.equals(user.getId())) {
            return ResponseEntity.ok(userService.updateUser(id, user));
        }

        return ResponseEntity.badRequest().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
