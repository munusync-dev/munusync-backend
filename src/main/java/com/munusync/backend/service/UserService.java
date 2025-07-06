package com.munusync.backend.service;
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service  // ← Makes this a Spring bean
public class UserService { 

    @Autowired  // ← Injects the repository automatically
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();  // Gets all users from DB
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);  // Finds one user
    }

    public User createUser(User user) {
        return userRepository.save(user);  // Saves new user
    }

    public User updateUser(Long id, User user) {
        user.setId(id);  // Sets the ID before saving
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);  // Deletes by ID
    }
}