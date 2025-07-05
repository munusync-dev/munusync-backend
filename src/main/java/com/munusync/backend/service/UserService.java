package com.munusync.backend.service;

<<<<<<< HEAD
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
=======
import com.munusync.backend.dtos.UserDto;
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .toList();
    }

    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail()));
>>>>>>> 85852505ceee28e61442811cd688f849a908978b
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
<<<<<<< HEAD
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existing = optionalUser.get();
            existing.setName(updatedUser.getName());
            existing.setEmail(updatedUser.getEmail());
            return userRepository.save(existing);
        }
        return null;
=======
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null)
            throw new RuntimeException("User not found");

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
>>>>>>> 85852505ceee28e61442811cd688f849a908978b
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
<<<<<<< HEAD
=======

>>>>>>> 85852505ceee28e61442811cd688f849a908978b
}
