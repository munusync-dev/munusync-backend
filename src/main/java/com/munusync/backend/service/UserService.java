package com.munusync.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.munusync.backend.dto.request.createUserRequestDTO;
import com.munusync.backend.dto.response.createUserResponseDTO;
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not fount with id =" + id));
    }

    public createUserResponseDTO createUser(createUserRequestDTO user) {

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());

        User savedUser = userRepository.save(newUser);

        return createUserResponseDTO.builder().id(savedUser.getId()).name(user.getName()).email(user.getEmail())
                .build();
    }

    public User updateUser(UUID id, User user) {

        User userToUpdate = getUserById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

}