package com.munusync.backend.service;

import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //injecting the userRepository
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //read all users.
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    // read user by id.
    //Optional used because a container object which may or may not contain a non-null value
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    //create a new user.
    public User createUser(User user){
        return userRepository.save(user);
    }
    // update an existing user.
    public Optional<User> updateUser(Long id, User user){
        Optional<User> exist = userRepository.findById(id);
        if(exist.isPresent()){
            User newUser = exist.get();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            userRepository.save(newUser);
        }
        return exist;
    }
    //delete user.
    public void deleteUser(Long id){
         userRepository.deleteById(id);
    }



}
