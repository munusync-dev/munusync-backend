package com.munusync.backend.controller;

import com.munusync.backend.dto.*;
import com.munusync.backend.model.User;
import com.munusync.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController - REST API endpoints for User operations
 * Provides all the HTTP endpoints to manage users
 * 
 * Base URL: /api/users
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  /**
   * GET /api/users - Get all users
   * 
   * @return List of all users
   */
  @GetMapping
  public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllUsers() {
    log.info("GET /api/users - Fetching all users");
    List<UserResponseDto> users = userService.getAllUsers();
    return ResponseEntity.ok(
        ApiResponseDto.success("Users retrieved successfully", users));
  }

  /**
   * GET /api/users/{id} - Get user by ID
   * 
   * @param id User ID
   * @return User data
   */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponseDto<UserResponseDto>> getUserById(@PathVariable Long id) {
    log.info("GET /api/users/{} - Fetching user by ID", id);
    UserResponseDto user = userService.getUserById(id);
    return ResponseEntity.ok(
        ApiResponseDto.success("User retrieved successfully", user));
  }

  /**
   * GET /api/users/username/{username} - Get user by username
   * 
   * @param username Username
   * @return User data
   */
  @GetMapping("/username/{username}")
  public ResponseEntity<ApiResponseDto<UserResponseDto>> getUserByUsername(@PathVariable String username) {
    log.info("GET /api/users/username/{} - Fetching user by username", username);
    UserResponseDto user = userService.getUserByUsername(username);
    return ResponseEntity.ok(
        ApiResponseDto.success("User retrieved successfully", user));
  }

  /**
   * GET /api/users/email/{email} - Get user by email
   * 
   * @param email Email
   * @return User data
   */
  @GetMapping("/email/{email}")
  public ResponseEntity<ApiResponseDto<UserResponseDto>> getUserByEmail(@PathVariable String email) {
    log.info("GET /api/users/email/{} - Fetching user by email", email);
    UserResponseDto user = userService.getUserByEmail(email);
    return ResponseEntity.ok(
        ApiResponseDto.success("User retrieved successfully", user));
  }

  /**
   * POST /api/users - Create a new user
   * 
   * @param createRequest User creation data
   * @return Created user data
   */
  @PostMapping
  public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(
      @Valid @RequestBody UserCreateRequestDto createRequest) {
    log.info("POST /api/users - Creating new user with username: {}", createRequest.getUsername());
    UserResponseDto createdUser = userService.createUser(createRequest);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ApiResponseDto.success("User created successfully", createdUser));
  }

  /**
   * PUT /api/users/{id} - Update an existing user
   * 
   * @param id            User ID to update
   * @param updateRequest Update data
   * @return Updated user data
   */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(
      @PathVariable Long id,
      @Valid @RequestBody UserUpdateRequestDto updateRequest) {
    log.info("PUT /api/users/{} - Updating user", id);
    UserResponseDto updatedUser = userService.updateUser(id, updateRequest);
    return ResponseEntity.ok(
        ApiResponseDto.success("User updated successfully", updatedUser));
  }

  /**
   * DELETE /api/users/{id} - Delete a user (soft delete)
   * 
   * @param id User ID to delete
   * @return Success message
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long id) {
    log.info("DELETE /api/users/{} - Deleting user", id);
    userService.deleteUser(id);
    return ResponseEntity.ok(
        ApiResponseDto.success("User deleted successfully"));
  }

  /**
   * DELETE /api/users/{id}/permanent - Permanently delete a user
   * 
   * @param id User ID to permanently delete
   * @return Success message
   */
  @DeleteMapping("/{id}/permanent")
  public ResponseEntity<ApiResponseDto<Void>> permanentlyDeleteUser(@PathVariable Long id) {
    log.info("DELETE /api/users/{}/permanent - Permanently deleting user", id);
    userService.permanentlyDeleteUser(id);
    return ResponseEntity.ok(
        ApiResponseDto.success("User permanently deleted successfully"));
  }

  /**
   * GET /api/users/active - Get all active users
   * 
   * @return List of active users
   */
  @GetMapping("/active")
  public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getActiveUsers() {
    log.info("GET /api/users/active - Fetching active users");
    List<UserResponseDto> activeUsers = userService.getActiveUsers();
    return ResponseEntity.ok(
        ApiResponseDto.success("Active users retrieved successfully", activeUsers));
  }

  /**
   * GET /api/users/role/{role} - Get users by role
   * 
   * @param role User role
   * @return List of users with the specified role
   */
  @GetMapping("/role/{role}")
  public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getUsersByRole(@PathVariable User.Role role) {
    log.info("GET /api/users/role/{} - Fetching users by role", role);
    List<UserResponseDto> users = userService.getUsersByRole(role);
    return ResponseEntity.ok(
        ApiResponseDto.success("Users retrieved successfully", users));
  }

  /**
   * GET /api/users/search?name={name} - Search users by name
   * 
   * @param name Name to search for
   * @return List of users matching the name
   */
  @GetMapping("/search")
  public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> searchUsersByName(@RequestParam String name) {
    log.info("GET /api/users/search?name={} - Searching users by name", name);
    List<UserResponseDto> users = userService.searchUsersByName(name);
    return ResponseEntity.ok(
        ApiResponseDto.success("Users retrieved successfully", users));
  }

  /**
   * GET /api/users/count/active - Get count of active users
   * 
   * @return Number of active users
   */
  @GetMapping("/count/active")
  public ResponseEntity<ApiResponseDto<Long>> getActiveUserCount() {
    log.info("GET /api/users/count/active - Getting active user count");
    long count = userService.getActiveUserCount();
    return ResponseEntity.ok(
        ApiResponseDto.success("Active user count retrieved successfully", count));
  }
}
