package com.munusync.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munusync.backend.entity.User;
import com.munusync.backend.service.UserService;

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

    //post
    @PostMapping
    public void createUser( @RequestBody User user){
        userService.createUser(user);
    }

    //get
    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    //Update
    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody User user){
        if (id == user.getId()) {
            userService.updateUser(id, user);
        }
    }
    

    //Delete
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        userService.deleteUser(id);
    }

}
