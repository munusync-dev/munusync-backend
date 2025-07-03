package com.munusync.backend.dto.request;

import java.time.LocalDate;

public class UserRegistrationRequestDTO {
    private final String username;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;
    private final boolean acceptTerms;

    private UserRegistrationRequestDTO(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.phoneNumber = builder.phoneNumber;
        this.acceptTerms = builder.acceptTerms;

        if (username == null || email == null || password == null ||
            firstName == null || lastName == null || !acceptTerms) {
            throw new IllegalStateException("Missing required fields or terms not accepted.");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isAcceptTerms() { return acceptTerms; }

    @Override
    public String toString() {
        return "UserRegistrationRequestDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", acceptTerms=" + acceptTerms +
                '}';
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String phoneNumber;
        private boolean acceptTerms;

        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
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
        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder acceptTerms(boolean acceptTerms) {
            this.acceptTerms = acceptTerms;
            return this;
        }

        public UserRegistrationRequestDTO build() {
            return new UserRegistrationRequestDTO(this);
        }
    }
}