package com.example.servermorracineseadvanced.api.model;

public class User {
    private String id;
    private String username;
    private Integer elo;
    public User(String id, String username, Integer elo){
        this.id = id;
        this.username = username;
        this.elo = elo;
    }

    public String getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public Integer getElo(){
        return elo;
    }
}
