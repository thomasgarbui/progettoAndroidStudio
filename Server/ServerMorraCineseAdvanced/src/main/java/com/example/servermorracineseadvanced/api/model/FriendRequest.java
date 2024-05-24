package com.example.servermorracineseadvanced.api.model;

public class FriendRequest {
    private Integer id;
    private String senderId;
    private String receiverId;
    private String status;
    public FriendRequest(Integer id, String senderId, String receiverId, String status){
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public Integer getId(){
        return id;
    }
    public String getSenderId(){
        return senderId;
    }
    public String getReceiverId(){
        return receiverId;
    }
    public String getStatus(){
        return status;
    }
}


