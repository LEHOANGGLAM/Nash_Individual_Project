package com.hoanglam.ecommerce.service.impl;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.ProductResponseDto;
import com.hoanglam.ecommerce.dto.response.UserResponseDto;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.ProductRepository;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    public static final int pageSize = 20;

    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not exist with id: " + id));
        return user;
    }

    @Override
    public List<User> getAllUsers(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String kw = params.getOrDefault("username", "");

        Page<User> result  = userRepository.findByUsernameContainingOrderByUsernameAsc(kw ,pageable);
        return result.getContent();
    }

    @Override
    public List<User> getUsersByRole(Map<String, String> params) {
        Pageable pageable = PageRequest.of(Integer.parseInt(params.getOrDefault("page", "0")), pageSize);
        String kw = params.getOrDefault("username", "");
        String id = params.get("roleId");

        Page<User> result;
        if (id != null && id != "") {
            result = userRepository.findByRolesCollection_IdAndUsernameContainingOrderByUsernameAsc(id, kw, pageable);
        } else {
            result = userRepository.findByUsernameContainingOrderByUsernameAsc(kw ,pageable);
        }

        return result.getContent();
    }

//    @Override
//    public User createUser(User u) {
//        UUID uuid = UUID.randomUUID();
//
//        u.setId(uuid.toString());
//        u.setActive(true);
//        u.setRegisteredDate(new Date());
//
//        User savedUser = userRepository.save(u);
//        return savedUser;
//    }

    @Override
    public UserResponseDto updateUser(String id, User userUpdate) {
        if(userRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("User not exist with id: " + id);
        }
        userUpdate = userRepository.save(userUpdate);
        UserResponseDto userResponseDto = modelMapper.map(userUpdate, UserResponseDto.class);
        return userResponseDto;
    }

    @Override
    public DeleteResponseDto softDeleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new ResourceNotFoundException("User not exist with id: " + id);
        }

        User user = userOptional.get();
        user.setActive(false);
        userRepository.save(user);

        return new DeleteResponseDto("Delete user successfully", HttpStatus.OK.value(), HttpStatus.OK);
    }
}
