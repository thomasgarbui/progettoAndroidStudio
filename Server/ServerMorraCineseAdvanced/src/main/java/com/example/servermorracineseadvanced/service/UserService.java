package com.example.servermorracineseadvanced.service;

import com.example.servermorracineseadvanced.api.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> userList;
    public UserService(){
        userList = new ArrayList<>();
        User s1 = new User(1,"Aldo");
        User s2 = new User(2,"Giovanni");
        User s3 = new User(3,"Giacomo");
        userList.addAll(Arrays.asList(s1,s2,s3));
    }

    public Optional<User> getUser(Integer id){
        Optional optional = Optional.empty();
        for(User user: userList){
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
