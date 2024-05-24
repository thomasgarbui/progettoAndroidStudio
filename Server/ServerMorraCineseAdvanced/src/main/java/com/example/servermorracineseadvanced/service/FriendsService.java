package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    private DataManager dataManager;
    public FriendsService(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public boolean newRequest(String senderUsername,String receiverUsername){
        boolean result = false;
        if(!senderUsername.isEmpty() && !receiverUsername.isEmpty()){
            result = dataManager.newRequest(senderUsername,receiverUsername);
        }
        return result;
    }
    public List<FriendRequest> getRequests(String senderUsername){
        List<FriendRequest> friendRequestList = new ArrayList<>();
        if(!senderUsername.isEmpty()){
            friendRequestList = dataManager.getRequests(senderUsername);
        }
        return friendRequestList;
    }
    public List<Friend> getFriends(String username){
        List<Friend> friendList = new ArrayList<>();
        if(!username.isEmpty()){
            friendList = dataManager.getFriends(username);
        }
        return friendList;
    }
    public boolean modifyRequest(String senderUsername,String receiverUsername,String state){
        boolean result = false;
        if(!senderUsername.isEmpty() && !receiverUsername.isEmpty() && !state.isEmpty()){
            result = dataManager.modifyRequest(senderUsername,receiverUsername,state);
        }
        return result;
    }
}
