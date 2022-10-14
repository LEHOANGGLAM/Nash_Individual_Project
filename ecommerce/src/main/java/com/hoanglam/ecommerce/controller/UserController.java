package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.entites.Product;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.exception.ResourceNotFoundException;
import com.hoanglam.ecommerce.repository.UserRepository;
import com.hoanglam.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }



    @GetMapping("/admin/users")
    public List<User> getAllUsers(@RequestParam Map<String, String> params) {
        return  userService.getAllUsers(params);
    }

    @GetMapping("/admin/users/role")
    public List<User> getUserByRole(@RequestParam Map<String, String> params) {
        return  userService.getUsersByRole(params);
    }
}
