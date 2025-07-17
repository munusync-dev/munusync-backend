package com.munusync.backend.Service.Interface;

import com.munusync.backend.Dto.Request.*;
import com.munusync.backend.Dto.Response.*;

public interface IAuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}
