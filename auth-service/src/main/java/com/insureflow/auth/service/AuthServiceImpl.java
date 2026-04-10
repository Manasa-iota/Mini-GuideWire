package com.insureflow.auth.service;

import com.insureflow.auth.dto.AuthResponse;
import com.insureflow.auth.dto.LoginRequest;
import com.insureflow.auth.dto.RegisterRequest;
import com.insureflow.auth.entity.AuthUser;
import com.insureflow.auth.repository.AuthUserRepository;
import com.insureflow.auth.security.JwtService;
import com.insureflow.common.exception.DomainException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DomainException("AUTH_DUPLICATE", "Email already exists");
        }
        AuthUser user = new AuthUser();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(request.role());
        AuthUser saved = userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(saved), saved.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        AuthUser user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new DomainException("AUTH_INVALID", "Invalid credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new DomainException("AUTH_INVALID", "Invalid credentials");
        }
        return new AuthResponse(jwtService.generateToken(user), user.getRole().name());
    }
}
