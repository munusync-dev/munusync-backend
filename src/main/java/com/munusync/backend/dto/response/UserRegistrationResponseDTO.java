package com.munusync.backend.dto.response;

public class UserRegistrationResponseDTO {
    private final Long userId;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String status;
    private final String message;
    private final boolean emailVerificationRequired;

    private UserRegistrationResponseDTO(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.status = builder.status != null ? builder.status : "PENDING";
        this.message = builder.message != null ? builder.message : "Registration in progress";
        this.emailVerificationRequired = builder.emailVerificationRequired;

        if (userId == null || username == null || email == null ||
            firstName == null || lastName == null) {
            throw new IllegalStateException("Missing required response fields.");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public boolean isEmailVerificationRequired() { return emailVerificationRequired; }

    @Override
    public String toString() {
        return "UserRegistrationResponseDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", emailVerificationRequired=" + emailVerificationRequired +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String status;
        private String message;
        private boolean emailVerificationRequired = false;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        public Builder emailVerificationRequired(boolean emailVerificationRequired) {
            this.emailVerificationRequired = emailVerificationRequired;
            return this;
        }

        public UserRegistrationResponseDTO build() {
            return new UserRegistrationResponseDTO(this);
        }
    }
}