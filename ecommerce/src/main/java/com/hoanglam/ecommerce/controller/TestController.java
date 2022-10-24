package com.hoanglam.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class TestController {
    @GetMapping("/all")
    public String allAccess(){
        return "Public content";
    }


    @GetMapping("/user")
    public String userAccess(){
        return "User content";
    }
    @GetMapping("/admin")
    public String adminAccess(){
        return "Admin content";
    }

}
