package com.munusync.backend.controller;

import com.munusync.backend.dto.ApiResponseDto;
import com.munusync.backend.dto.UserCreateRequestDto;
import com.munusync.backend.dto.UserResponseDto;
import com.munusync.backend.model.User;
import com.munusync.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DemoController - Simple endpoints to demonstrate and test the User API
 * This controller provides easy-to-use endpoints for testing purposes
 * 
 * Base URL: /api/demo
 */
@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

  private final UserService userService;

  /**
   * GET /api/demo/hello - Simple hello endpoint
   * 
   * @return Welcome message
   */
  @GetMapping("/hello")
  public ResponseEntity<ApiResponseDto<String>> hello() {
    log.info("GET /api/demo/hello - Demo hello endpoint called");
    return ResponseEntity.ok(
        ApiResponseDto.success("Hello from MunuSync User Management System!",
            "Welcome to the User API Demo!"));
  }

  /**
   * POST /api/demo/create-sample-users - Create sample users for testing
   * 
   * @return List of created sample users
   */
  @PostMapping("/create-sample-users")
  public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> createSampleUsers() {
    log.info("POST /api/demo/create-sample-users - Creating sample users");

    try {
      // Sample user 1 - Admin
      UserCreateRequestDto admin = new UserCreateRequestDto();
      admin.setUsername("admin");
      admin.setEmail("admin@munusync.com");
      admin.setFirstName("Admin");
      admin.setLastName("User");
      admin.setPassword("admin123");
      admin.setRole(User.Role.ADMIN);

      // Sample user 2 - Regular user
      UserCreateRequestDto john = new UserCreateRequestDto();
      john.setUsername("john_doe");
      john.setEmail("john.doe@example.com");
      john.setFirstName("John");
      john.setLastName("Doe");
      john.setPassword("password123");
      john.setRole(User.Role.USER);

      // Sample user 3 - Moderator
      UserCreateRequestDto jane = new UserCreateRequestDto();
      jane.setUsername("jane_smith");
      jane.setEmail("jane.smith@example.com");
      jane.setFirstName("Jane");
      jane.setLastName("Smith");
      jane.setPassword("password123");
      jane.setRole(User.Role.MODERATOR);

      // Create users
      UserResponseDto createdAdmin = userService.createUser(admin);
      UserResponseDto createdJohn = userService.createUser(john);
      UserResponseDto createdJane = userService.createUser(jane);

      List<UserResponseDto> createdUsers = List.of(createdAdmin, createdJohn, createdJane);

      return ResponseEntity.ok(
          ApiResponseDto.success("Sample users created successfully", createdUsers));

    } catch (Exception e) {
      log.error("Error creating sample users: {}", e.getMessage());
      return ResponseEntity.ok(
          ApiResponseDto.success("Some sample users might already exist. Check /api/users to see all users.", null));
    }
  }

  /**
   * GET /api/demo/stats - Get user statistics
   * 
   * @return User statistics
   */
  @GetMapping("/stats")
  public ResponseEntity<ApiResponseDto<Object>> getUserStats() {
    log.info("GET /api/demo/stats - Getting user statistics");

    long totalUsers = userService.getAllUsers().size();
    long activeUsers = userService.getActiveUserCount();
    long adminUsers = userService.getUsersByRole(User.Role.ADMIN).size();
    long moderatorUsers = userService.getUsersByRole(User.Role.MODERATOR).size();
    long regularUsers = userService.getUsersByRole(User.Role.USER).size();

    var stats = new Object() {
      public final long total = totalUsers;
      public final long active = activeUsers;
      public final long admins = adminUsers;
      public final long moderators = moderatorUsers;
      public final long users = regularUsers;
    };

    return ResponseEntity.ok(
        ApiResponseDto.success("User statistics retrieved successfully", stats));
  }

  /**
   * GET /api/demo/endpoints - Get list of available API endpoints
   * 
   * @return List of available endpoints with descriptions
   */
  @GetMapping("/endpoints")
  public ResponseEntity<ApiResponseDto<Object>> getAvailableEndpoints() {
    log.info("GET /api/demo/endpoints - Getting available endpoints");

    var endpoints = new Object() {
      public final Object users = new Object() {
        public final String getAllUsers = "GET /api/users - Get all users";
        public final String getUserById = "GET /api/users/{id} - Get user by ID";
        public final String getUserByUsername = "GET /api/users/username/{username} - Get user by username";
        public final String getUserByEmail = "GET /api/users/email/{email} - Get user by email";
        public final String createUser = "POST /api/users - Create a new user";
        public final String updateUser = "PUT /api/users/{id} - Update an existing user";
        public final String deleteUser = "DELETE /api/users/{id} - Delete a user (soft delete)";
        public final String permanentlyDeleteUser = "DELETE /api/users/{id}/permanent - Permanently delete a user";
        public final String getActiveUsers = "GET /api/users/active - Get all active users";
        public final String getUsersByRole = "GET /api/users/role/{role} - Get users by role (USER, ADMIN, MODERATOR)";
        public final String searchUsers = "GET /api/users/search?name={name} - Search users by name";
        public final String getActiveUserCount = "GET /api/users/count/active - Get count of active users";
      };
      public final Object demo = new Object() {
        public final String hello = "GET /api/demo/hello - Simple hello endpoint";
        public final String createSampleUsers = "POST /api/demo/create-sample-users - Create sample users for testing";
        public final String stats = "GET /api/demo/stats - Get user statistics";
        public final String endpoints = "GET /api/demo/endpoints - Get this list of endpoints";
      };
    };

    return ResponseEntity.ok(
        ApiResponseDto.success("Available endpoints retrieved successfully", endpoints));
  }
}
