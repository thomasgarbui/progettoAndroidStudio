package com.example.morracineseadvanced.data.model;

public class UserModel {
    private String username;
    private String password;
    private String elo;
    public UserModel(String username, String password, String elo){
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
    public String getElo(){
        return elo;
    }
}
