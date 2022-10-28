package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.request.LoginRequest;
import com.hoanglam.ecommerce.dto.request.SignUpRequest;
import com.hoanglam.ecommerce.dto.response.JwtResponse;
import com.hoanglam.ecommerce.dto.response.entities.UserResponseDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    UserResponseDto getUserByJwtToken(HttpServletRequest headerToken);
    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
