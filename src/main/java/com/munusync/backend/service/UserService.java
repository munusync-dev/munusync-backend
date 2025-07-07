package com.munusync.backend.service;

import com.munusync.backend.dto.request.UserRegistrationRequestDTO;
import com.munusync.backend.dto.response.UserRegistrationResponseDTO;
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
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    }

    //create a new user.
    public UserRegistrationResponseDTO createUser(UserRegistrationRequestDTO dto){
        User user = User.builder()
                .name(dto.getFirstName()+" "+dto.getLastName())
                .email(dto.getEmail())
                .build();
        userRepository.save(user);
        return UserRegistrationResponseDTO.builder()
                .id(user.getId())
                .userName(user.getName())
                .message("User Created successfully\n")
                .build();
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
