package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private DataManager dataManager;
    public UserService(DataManager dataManager){
        this.dataManager = dataManager;
    }
    public User getUser(String username){
        User user = null;
        if(!username.isEmpty()){
            user = dataManager.getUser(username);
        }
        return user;
    }

    public List<User> getUsers(){
        List<User> userList = dataManager.getUsers();
        return userList;
    }
    public boolean login(String username,String password){
        boolean result = false;
        User user = getUser(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                result = true;
            }
        }
        return result;

    }

    public boolean register(String username,String password){
        boolean result = false;
        if(!username.isEmpty() && !password.isEmpty()){
            result = dataManager.register(username,password);
        }
        return result;
    }
}
