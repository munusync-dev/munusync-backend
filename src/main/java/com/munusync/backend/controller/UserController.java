package com.munusync.backend.controller;

import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;//for what?

import java.util.List;
import java.util.Optional;


@RestController
// When placed @RequestMapping above the class, it sets the base URL path for
// all methods inside that controller.
// since all methods are get..post/users means request in above class
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST create user post
    public void create(@RequestBody User user){
        userService.createUser(user);
    }

    //Get all users
    public List getAllUsers(){
        return userService.getAllUsers();
    }

    //Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        // the return explanation its like an if else if it exists or not
        //response entityIt's a Spring class that lets you customize
        // the full HTTP response — status code, headers, and body
        // If user is found, return 200 OK with the user data "ResponseEntity::ok"
        // If not found, return 404 Not Found.
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        //method is not void ts not enough to delete with no return
        return ResponseEntity.noContent().build();
}
}
