package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.UserResponseDto;
import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(String id);
    List<User> getAllUsers(Map<String, String> params);
    List<User> getUsersByRole(Map<String, String> params);
   // User createUser(User u);
    UserResponseDto updateUser(String id, User userUpdate);
    DeleteResponseDto softDeleteUser(String id);
}
