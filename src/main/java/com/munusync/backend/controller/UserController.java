package com.munusync.backend.controller;

import com.munusync.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.munusync.backend.entity.User;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    public UserController(UserService _service) {
        this.service = _service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(service.createUser(user));
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok((service.getAllUsers()));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<User>> getUserById(@PathVariable long id){
        return ResponseEntity.ok((service.getUserById(id)));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Optional<User>> updateUser(@PathVariable long id, @RequestBody User user){
        return ResponseEntity.ok((service.updateUser(id, user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        Optional<User> user = service.getUserById(id);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User with ID " + id + " not found.");
        }
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }



}
