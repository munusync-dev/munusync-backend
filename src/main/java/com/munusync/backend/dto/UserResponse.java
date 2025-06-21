package com.munusync.backend.dto;

import com.munusync.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * UserResponseDto - Used to send user data to the client
 * This DTO excludes sensitive information like password
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String fullName;
  private User.Role role;
  private Boolean active;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // Constructor to create DTO from User entity
  public UserResponseDto(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.fullName = user.getFullName();
    this.role = user.getRole();
    this.active = user.getActive();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }
}
