package com.example.clientmorracinese;

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

    @Override
    public String toString() {
        return "User{id='" + id + "', username='" + username + "', elo=" + elo + "}";
    }
}