package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/getUser")
    public User User(@RequestParam String username){
        User user = userService.getUser(username);
        if(user != null){
            return user;
        }
        return null;
    }
    @GetMapping("/getUsers")
    public List<User> Users(){
        List<User> user = userService.getUsers();
        if(!user.isEmpty()){
            return user;
        }
        return null;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String username,@RequestParam String password){
        return userService.login(username,password);
    }

    @PostMapping("/register")
    public boolean register(@RequestParam String username,@RequestParam String password){
        return userService.register(username,password);
    }
}
