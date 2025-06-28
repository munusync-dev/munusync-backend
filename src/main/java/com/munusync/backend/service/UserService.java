package com.munusync.backend.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public User createUser(User user){
        return userRepository.save(user) ;
    }

    public ResponseEntity<?> updateUser(Long id, User user){

        Optional<User> optionalUser = getUserById(id);

        if (optionalUser.isPresent() ) {
            
            User userRefrence = optionalUser.get();

            userRefrence.setName(user.getName());
            userRefrence.setEmail(user.getEmail());

            User updateUser = userRepository.save(userRefrence);
           
           return ResponseEntity.ok(updateUser);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteUser(Long id){
        if (this.getUserById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}