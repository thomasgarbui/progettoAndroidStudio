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
    public Optional<User> getUser(String username){
        Optional optional = Optional.empty();
        if(username.isEmpty()){
            optional = Optional.of(dataManager.getUser(username));
            return optional;
        }
        return optional;
    }

    public List<User> getUsers(){
        List<User> userList = dataManager.getUsers();
        return userList;
    }
    public boolean login(String username,String password){
        boolean result = false;
        Optional<User> optional = getUser(username);
        if(optional.isPresent()){
            User user = (User) optional.get();
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
