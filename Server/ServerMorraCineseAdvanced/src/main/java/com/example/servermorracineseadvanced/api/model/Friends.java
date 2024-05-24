package com.example.demo;

public class Friend {
    private String friendOneUsername;
    private String friendTwoUsername;

    public Friend(String friendOneUsername, String friendTwoUsername){
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
