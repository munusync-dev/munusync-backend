package com.munusync.backend.dto.request;
import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
