package com.insureflow.auth;

import com.insureflow.auth.dto.LoginRequest;
import com.insureflow.auth.dto.RegisterRequest;
import com.insureflow.auth.entity.Role;
import com.insureflow.auth.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Test
    void registerAndLoginShouldReturnToken() {
        var register = authService.register(new RegisterRequest("customer@example.com", "Password@123", Role.CUSTOMER));
        var login = authService.login(new LoginRequest("customer@example.com", "Password@123"));

        assertThat(register.token()).isNotBlank();
        assertThat(login.token()).isNotBlank();
    }
}
