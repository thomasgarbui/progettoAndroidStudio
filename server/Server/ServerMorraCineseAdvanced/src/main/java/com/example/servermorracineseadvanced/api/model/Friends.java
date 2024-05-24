package com.example.servermorracineseadvanced.api.model;

public class Friends {
    private String userId;
    private String friendId;

    public Friends(String userId, String friendId){
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getUserId(){
        return userId;
    }

    public String getFriendId(){
        return friendId;
    }
}
