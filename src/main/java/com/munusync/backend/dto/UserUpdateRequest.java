package com.munusync.backend.dto;

import com.munusync.backend.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserUpdateRequestDto - Used to receive data when updating an existing user
 * All fields are optional since user might want to update only some fields
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

  @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
  private String username;

  @Email(message = "Email should be valid")
  private String email;

  @Size(max = 50, message = "First name must not exceed 50 characters")
  private String firstName;

  @Size(max = 50, message = "Last name must not exceed 50 characters")
  private String lastName;

  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  private User.Role role;

  private Boolean active;

  // Helper method to check if any field is provided for update
  public boolean hasAnyFieldToUpdate() {
    return username != null || email != null || firstName != null ||
        lastName != null || password != null || role != null || active != null;
  }
}
