package com.munusync.backend.service;

import com.munusync.backend.dto.UserCreateRequestDto;
import com.munusync.backend.dto.UserResponseDto;
import com.munusync.backend.dto.UserUpdateRequestDto;
import com.munusync.backend.exception.ResourceNotFoundException;
import com.munusync.backend.exception.DuplicateResourceException;
import com.munusync.backend.model.User;
import com.munusync.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserService - Business logic for User operations
 * Contains all the business rules and operations related to users
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

  private final UserRepository userRepository;

  /**
   * Get all users
   * 
   * @return List of all users as DTOs
   */
  @Transactional(readOnly = true)
  public List<UserResponseDto> getAllUsers() {
    log.info("Fetching all users");
    return userRepository.findAll()
        .stream()
        .map(UserResponseDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Get user by ID
   * 
   * @param id User ID
   * @return User DTO
   * @throws ResourceNotFoundException if user not found
   */
  @Transactional(readOnly = true)
  public UserResponseDto getUserById(Long id) {
    log.info("Fetching user with id: {}", id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    return new UserResponseDto(user);
  }

  /**
   * Get user by username
   * 
   * @param username Username
   * @return User DTO
   * @throws ResourceNotFoundException if user not found
   */
  @Transactional(readOnly = true)
  public UserResponseDto getUserByUsername(String username) {
    log.info("Fetching user with username: {}", username);
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    return new UserResponseDto(user);
  }

  /**
   * Get user by email
   * 
   * @param email Email
   * @return User DTO
   * @throws ResourceNotFoundException if user not found
   */
  @Transactional(readOnly = true)
  public UserResponseDto getUserByEmail(String email) {
    log.info("Fetching user with email: {}", email);
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    return new UserResponseDto(user);
  }

  /**
   * Create a new user
   * 
   * @param createRequest User creation data
   * @return Created user DTO
   * @throws DuplicateResourceException if username or email already exists
   */
  public UserResponseDto createUser(UserCreateRequestDto createRequest) {
    log.info("Creating new user with username: {}", createRequest.getUsername());

    // Check if username already exists
    if (userRepository.existsByUsername(createRequest.getUsername())) {
      throw new DuplicateResourceException("Username already exists: " + createRequest.getUsername());
    }

    // Check if email already exists
    if (userRepository.existsByEmail(createRequest.getEmail())) {
      throw new DuplicateResourceException("Email already exists: " + createRequest.getEmail());
    }

    // Convert DTO to entity
    User user = createRequest.toUser();

    // TODO: Encrypt password here (for now keeping plain text for simplicity)
    // user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Save user
    User savedUser = userRepository.save(user);
    log.info("Successfully created user with id: {}", savedUser.getId());

    return new UserResponseDto(savedUser);
  }

  /**
   * Update an existing user
   * 
   * @param id            User ID to update
   * @param updateRequest Update data
   * @return Updated user DTO
   * @throws ResourceNotFoundException  if user not found
   * @throws DuplicateResourceException if new username or email already exists
   */
  public UserResponseDto updateUser(Long id, UserUpdateRequestDto updateRequest) {
    log.info("Updating user with id: {}", id);

    // Find existing user
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    // Check if new username already exists (if provided and different)
    if (updateRequest.getUsername() != null &&
        !updateRequest.getUsername().equals(existingUser.getUsername()) &&
        userRepository.existsByUsername(updateRequest.getUsername())) {
      throw new DuplicateResourceException("Username already exists: " + updateRequest.getUsername());
    }

    // Check if new email already exists (if provided and different)
    if (updateRequest.getEmail() != null &&
        !updateRequest.getEmail().equals(existingUser.getEmail()) &&
        userRepository.existsByEmail(updateRequest.getEmail())) {
      throw new DuplicateResourceException("Email already exists: " + updateRequest.getEmail());
    }

    // Update fields if provided
    if (updateRequest.getUsername() != null) {
      existingUser.setUsername(updateRequest.getUsername());
    }
    if (updateRequest.getEmail() != null) {
      existingUser.setEmail(updateRequest.getEmail());
    }
    if (updateRequest.getFirstName() != null) {
      existingUser.setFirstName(updateRequest.getFirstName());
    }
    if (updateRequest.getLastName() != null) {
      existingUser.setLastName(updateRequest.getLastName());
    }
    if (updateRequest.getPassword() != null) {
      // TODO: Encrypt password
      existingUser.setPassword(updateRequest.getPassword());
    }
    if (updateRequest.getRole() != null) {
      existingUser.setRole(updateRequest.getRole());
    }
    if (updateRequest.getActive() != null) {
      existingUser.setActive(updateRequest.getActive());
    }

    // Save updated user
    User updatedUser = userRepository.save(existingUser);
    log.info("Successfully updated user with id: {}", updatedUser.getId());

    return new UserResponseDto(updatedUser);
  }

  /**
   * Delete a user (soft delete by setting active = false)
   * 
   * @param id User ID to delete
   * @throws ResourceNotFoundException if user not found
   */
  public void deleteUser(Long id) {
    log.info("Deleting user with id: {}", id);

    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    // Soft delete - set active to false
    user.setActive(false);
    userRepository.save(user);

    log.info("Successfully deleted user with id: {}", id);
  }

  /**
   * Permanently delete a user from database
   * 
   * @param id User ID to delete permanently
   * @throws ResourceNotFoundException if user not found
   */
  public void permanentlyDeleteUser(Long id) {
    log.info("Permanently deleting user with id: {}", id);

    if (!userRepository.existsById(id)) {
      throw new ResourceNotFoundException("User not found with id: " + id);
    }

    userRepository.deleteById(id);
    log.info("Successfully permanently deleted user with id: {}", id);
  }

  /**
   * Get all active users
   * 
   * @return List of active users
   */
  @Transactional(readOnly = true)
  public List<UserResponseDto> getActiveUsers() {
    log.info("Fetching all active users");
    return userRepository.findByActiveTrue()
        .stream()
        .map(UserResponseDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Get users by role
   * 
   * @param role User role
   * @return List of users with the specified role
   */
  @Transactional(readOnly = true)
  public List<UserResponseDto> getUsersByRole(User.Role role) {
    log.info("Fetching users with role: {}", role);
    return userRepository.findByRole(role)
        .stream()
        .map(UserResponseDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Search users by name (first name or last name)
   * 
   * @param name Name to search for
   * @return List of users matching the name
   */
  @Transactional(readOnly = true)
  public List<UserResponseDto> searchUsersByName(String name) {
    log.info("Searching users by name: {}", name);
    return userRepository.findByNameContaining(name)
        .stream()
        .map(UserResponseDto::new)
        .collect(Collectors.toList());
  }

  /**
   * Get count of active users
   * 
   * @return Number of active users
   */
  @Transactional(readOnly = true)
  public long getActiveUserCount() {
    log.info("Getting active user count");
    return userRepository.countByActiveTrue();
  }
}
