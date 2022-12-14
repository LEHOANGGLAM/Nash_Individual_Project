package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.config.jwt.AuthTokenFilter;
import com.hoanglam.ecommerce.config.jwt.JwtUtils;
import com.hoanglam.ecommerce.dto.request.LoginRequest;
import com.hoanglam.ecommerce.dto.request.SignUpRequest;
import com.hoanglam.ecommerce.dto.response.ErrorResponse;
import com.hoanglam.ecommerce.dto.response.JwtResponse;
import com.hoanglam.ecommerce.dto.response.SuccessResponse;
import com.hoanglam.ecommerce.dto.response.entities.UserResponseDto;
import com.hoanglam.ecommerce.entites.Role;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.MessageException;
import com.hoanglam.ecommerce.exception.ResourceAlreadyExistException;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.RoleRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.AuthService;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthTokenFilter AuthTokenFilter;

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //System.out.println( Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.isEnabled(),
                roles);
    }

    @Override
    public UserResponseDto getUserByJwtToken(HttpServletRequest headerToken) {
        String token = AuthTokenFilter.parseJwt(headerToken);
        String username = jwtUtils.getUserNameFromJwtToken(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        return userResponseDto;
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageException("Error: Username is already in use!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageException("Error: Email is already in use!"));
        }
        if (userRepository.existsByMobile(signUpRequest.getMobile())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageException("Error: This phone is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getMobile());

        Collection<String> strRoles = signUpRequest.getRole();
        Collection<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName("admin")
                            .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "user":
                    Role modRole = roleRepository.findByName("user")
                            .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
                    roles.add(modRole);
                    break;
            }
        });

        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setRolesCollection(roles);
        user.setActive(true);
        user.setRegisteredDate(new Date());
        userRepository.save(user);
        return ResponseEntity.ok(new SuccessResponse("User registered successfully!"));
    }
}
