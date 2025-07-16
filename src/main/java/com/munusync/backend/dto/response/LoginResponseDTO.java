package com.munusync.backend.dto.response;

public class LoginResponseDTO {
    private final Long userId;
    private final String name;
    private final String token;

    private LoginResponseDTO(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.token = builder.token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long userId;
        private String name;
        private String token;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(this);
        }
    }
}
