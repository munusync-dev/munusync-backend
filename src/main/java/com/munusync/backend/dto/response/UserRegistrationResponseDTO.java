package com.munusync.backend.dto.response;

public class UserRegistrationResponseDTO {

    private final int id;
    private final String UserName;
    private final String Message;

    public UserRegistrationResponseDTO(Builder builder) {
        this.id = builder.id;
        this.UserName = builder.UserName;
        this.Message = builder.Message;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getMessage() {
        return Message;
    }

    public static Builder builder(){
      return new Builder();
    }

    public static class Builder {
        private int id;
        private String UserName;
        private String Message;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.UserName = username;
            return this;
        }

        public Builder message(String message) {
            this.Message = message;
            return this;
        }

        public UserRegistrationResponseDTO build() {
            return new UserRegistrationResponseDTO(this);
        }

    }


}
