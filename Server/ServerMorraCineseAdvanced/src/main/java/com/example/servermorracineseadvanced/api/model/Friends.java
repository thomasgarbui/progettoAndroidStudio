package com.example.servermorracineseadvanced.api.model;

public class Friends {
    private String friendOneUsername;
    private String friendTwoUsername;

    public Friends(String friendOneUsername, String friendTwoUsername){
        this.friendOneUsername = friendOneUsername;
        this.friendTwoUsername = friendTwoUsername;
    }

    public String getFriendOneUsername(){
        return friendOneUsername;
    }

    public String getFriendTwoUsername(){
        return friendTwoUsername;
    }
}
