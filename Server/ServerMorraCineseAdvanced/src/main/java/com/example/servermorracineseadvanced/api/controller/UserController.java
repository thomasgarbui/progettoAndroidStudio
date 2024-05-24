package com.example.servermorracineseadvanced.api.controller;
import com.example.servermorracineseadvanced.api.model.User;

import com.example.servermorracineseadvanced.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/getUser")
    public User getUser(@RequestParam String id){
        Optional user = userService.getUser(id);
        if(user.isPresent()){
            return (User) user.get();
        }
        return null;
    }
    @PostMapping("/newUser")
    public boolean newUser(@RequestParam String id,@RequestParam String username){
        User user = userService.newUser(id,username);
        if(user != null){
            return true;
        }
        return false;
    }
}
