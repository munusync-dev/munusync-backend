package com.munusync.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ApiResponseDto - Generic response wrapper for API endpoints
 * Provides consistent response structure across all endpoints
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {

  private boolean success;
  private String message;
  private T data;
  private LocalDateTime timestamp;

  // Constructor for successful response with data
  public ApiResponseDto(String message, T data) {
    this.success = true;
    this.message = message;
    this.data = data;
    this.timestamp = LocalDateTime.now();
  }

  // Constructor for successful response without data
  public ApiResponseDto(String message) {
    this.success = true;
    this.message = message;
    this.data = null;
    this.timestamp = LocalDateTime.now();
  }

  // Static method for success response with data
  public static <T> ApiResponseDto<T> success(String message, T data) {
    return new ApiResponseDto<>(message, data);
  }

  // Static method for success response without data
  public static <T> ApiResponseDto<T> success(String message) {
    return new ApiResponseDto<>(message);
  }

  // Static method for error response
  public static <T> ApiResponseDto<T> error(String message) {
    ApiResponseDto<T> response = new ApiResponseDto<>();
    response.setSuccess(false);
    response.setMessage(message);
    response.setData(null);
    response.setTimestamp(LocalDateTime.now());
    return response;
  }
}
