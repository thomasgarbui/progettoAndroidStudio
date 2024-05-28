package com.example.demo;

public class FriendRequest {
    private String senderUsername;
    private String receiverUsername;
    private String status;
    public FriendRequest( String senderUsername, String receiverUsername, String status){
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.status = status;
    }

    public String getSenderUsername(){
        return senderUsername;
    }
    public String getReceiverUsername(){
        return receiverUsername;
    }
    public String getStatus(){
        return status;
    }
}


