package com.example.servermorracineseadvanced.api.model;

public class FriendRequest {
    private Integer id;
    private String senderUsername;
    private String receiverUsername;
    private String status;
    public FriendRequest(Integer id, String senderUsername, String receiverUsername, String status){
        this.id = id;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.status = status;
    }

    public Integer getId(){
        return id;
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


