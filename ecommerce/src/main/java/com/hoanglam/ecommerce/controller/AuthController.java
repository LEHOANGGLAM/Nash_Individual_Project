package com.hoanglam.ecommerce.controller;


import com.hoanglam.ecommerce.config.jwt.JwtUtils;
import com.hoanglam.ecommerce.dto.response.entities.UserResponseDto;
import com.hoanglam.ecommerce.entites.Role;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.repository.RoleRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.dto.request.LoginRequest;
import com.hoanglam.ecommerce.dto.request.SignUpRequest;
import com.hoanglam.ecommerce.dto.response.JwtResponse;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.service.AuthService;
import com.hoanglam.ecommerce.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @GetMapping("/user")
    public UserResponseDto getUserByToken(HttpServletRequest headerToken) {
        return authService.getUserByJwtToken(headerToken);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}
