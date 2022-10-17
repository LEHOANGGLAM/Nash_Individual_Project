package com.hoanglam.ecommerce.service;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(String id);
    List<User> getAllUsers(Map<String, String> params);
    List<User> getUsersByRole(Map<String, String> params);
    User createUser(User u);
    User updateUser(String id, User userUpdate);
    boolean deleteUser(String id);
}