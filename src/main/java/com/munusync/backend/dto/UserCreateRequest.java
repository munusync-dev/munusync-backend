package com.munusync.backend.dto;

import com.munusync.backend.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserCreateRequestDto - Used to receive data when creating a new user
 * Contains all the required fields for user creation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "First name is required")
  @Size(max = 50, message = "First name must not exceed 50 characters")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Size(max = 50, message = "Last name must not exceed 50 characters")
  private String lastName;

  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  private User.Role role = User.Role.USER; // Default role

  // Convert DTO to User entity
  public User toUser() {
    User user = new User();
    user.setUsername(this.username);
    user.setEmail(this.email);
    user.setFirstName(this.firstName);
    user.setLastName(this.lastName);
    user.setPassword(this.password); // Note: Should be encrypted in service
    user.setRole(this.role != null ? this.role : User.Role.USER);
    user.setActive(true);
    return user;
  }
}
