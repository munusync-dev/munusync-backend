package com.munusync.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void createUser(User user){
        this.userRepository.save(user);
    }

    public void updateUser(Long id, User user){

        Optional<User> optionalUser = getUserById(id);

        if (optionalUser.isPresent() ) {
            
            User userRefrence = optionalUser.get();

            userRefrence.setName(user.getName());
            userRefrence.setEmail(user.getEmail());
           
            userRepository.save(userRefrence);
        }
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
}