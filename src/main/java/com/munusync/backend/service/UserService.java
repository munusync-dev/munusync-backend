package com.munusync.backend.service;
import com.munusync.backend.dto.request.UserRequest;
import com.munusync.backend.dto.response.UserResponse;
import com.munusync.backend.entity.User;
import com.munusync.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; //this is for @service

//We import List because it's not a basic type ,it is a Java utility class
// it must be imported to handle lists like multiple users.
import java.util.List;
//same as lists but optionak handle values that might be missing,
//like a user that may not exist.
import java.util.Optional;

@Service
public class UserService {

    @Autowired // automatically inject dependencies of the project
    private UserRepository userRepository;

    // CREATE a new user
    public User createUser(User user) {
        return userRepository.save(user);

    }

    // READ all users
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    // READ a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);// Optional avoids null pointer issues
    }

    // UPDATE a user
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //update the fields that are allowed to change
            //not a must name or email depends on update functionality
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // DELETE a user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }

    public User fromRequest(UserRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public User createUserFromDTO(UserRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return userRepository.save(user);
    }


    //verify method
}

