package com.example.servermorracineseadvanced.api.model;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    public User(Integer id, String name,Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
}
