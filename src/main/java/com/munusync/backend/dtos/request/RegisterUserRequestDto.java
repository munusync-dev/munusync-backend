package com.munusync.backend.dtos.request;

public class RegisterUserRequestDto {
    private final String name;
    private final String email;

    private RegisterUserRequestDto(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public RegisterUserRequestDto build() {
            return new RegisterUserRequestDto(this);
        }
    }
}
