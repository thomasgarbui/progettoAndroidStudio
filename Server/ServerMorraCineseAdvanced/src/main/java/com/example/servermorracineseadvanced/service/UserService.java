package com.example.servermorracineseadvanced.service;

import com.example.servermorracineseadvanced.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList;
    public UserService(){
        userList = new ArrayList<>();
        User s1 = new User(1,"Aldo",40);
        User s2 = new User(2,"Giovanni",30);
        User s3 = new User(3,"Giacomo",20);
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
