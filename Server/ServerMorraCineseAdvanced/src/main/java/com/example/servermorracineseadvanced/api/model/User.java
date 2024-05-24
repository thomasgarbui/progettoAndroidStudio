package com.example.demo;

public class User {
    private String username;
    private String password;
    private Integer elo;
    public User(String username, String password, Integer elo){
        this.username = username;
        this.password = password;
        this.elo = elo;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Integer getElo(){
        return elo;
    }
}
