package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.LoginRequest;
import com.hoanglam.ecommerce.dto.request.SignUpRequest;
import com.hoanglam.ecommerce.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
