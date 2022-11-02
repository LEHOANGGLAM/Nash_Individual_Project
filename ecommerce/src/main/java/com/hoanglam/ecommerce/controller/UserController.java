package com.hoanglam.ecommerce.controller;

import com.hoanglam.ecommerce.dto.response.DeleteResponseDto;
import com.hoanglam.ecommerce.dto.response.entities.UserResponseDto;
import com.hoanglam.ecommerce.entites.User;
import com.hoanglam.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUserById(@PathVariable String id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }


    @GetMapping("")
    public List<UserResponseDto> getAllUsers(@RequestParam Map<String, String> params) {
        return  userService.getAllUsers(params);
    }

    @GetMapping("/role")
    public List<UserResponseDto> getUserByRole(@RequestParam Map<String, String> params) {
        return  userService.getUsersByRole(params);
    }

    @DeleteMapping("/{id}")
    public DeleteResponseDto deleteUser(@PathVariable String id) {
        return  this.userService.softDeleteUser(id);
    }
}
