package com.example.servermorracineseadvanced.service;

import com.example.servermorracineseadvanced.api.model.User;
import com.example.servermorracineseadvanced.database.DataManager;
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

    /*Optional optional = Optional.empty();
        for(User user: userList){
            if(id.equals(user.getId())){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;*/

    public Optional<User> getUser(String id){
        Optional optional = Optional.empty();
        for(User user: dataManager.getUser){
            if(id.equals(user.getId())){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
    public User newUser(String id,String username){
        if(id.length() > 0 && username.length() > 0){
            User user = new User(id,username,10);
            return user;
        }
        return null;
    }
}
