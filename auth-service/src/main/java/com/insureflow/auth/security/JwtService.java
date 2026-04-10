package com.insureflow.auth.security;

import com.insureflow.auth.entity.AuthUser;
import com.insureflow.common.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtService {
    private final JwtTokenProvider tokenProvider;

    public JwtService(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public String generateToken(AuthUser user) {
        return tokenProvider.generateToken(user.getEmail(), Map.of("role", user.getRole().name()));
    }
}
