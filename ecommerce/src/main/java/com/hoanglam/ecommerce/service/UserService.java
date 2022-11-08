package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.UserResponseDto;
import com.hoanglam.ecommerce.entites.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserResponseDto getUserById(String id);
    List<UserResponseDto> getAllUsers(Map<String, String> params);

   // User createUser(User u);
    UserResponseDto updateUser(String id, User userUpdate);
    DeleteResponseDto softDeleteUser(String id);
}
