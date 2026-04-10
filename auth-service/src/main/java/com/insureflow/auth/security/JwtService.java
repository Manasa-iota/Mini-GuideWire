package com.insureflow.auth.security;

import com.insureflow.auth.entity.AuthUser;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class JwtService {
    public String generateToken(AuthUser user) {
        String raw = user.getEmail() + ":" + user.getRole().name();
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }
}
