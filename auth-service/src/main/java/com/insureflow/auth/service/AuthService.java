package com.insureflow.auth.service;

import com.insureflow.auth.dto.AuthResponse;
import com.insureflow.auth.dto.LoginRequest;
import com.insureflow.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
