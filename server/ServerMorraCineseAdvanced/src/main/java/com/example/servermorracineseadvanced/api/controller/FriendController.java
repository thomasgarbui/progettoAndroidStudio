package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    private FriendsService friendsService;
    @Autowired
    public FriendController(FriendsService friendsService){
        this.friendsService = friendsService;
    }
    @PostMapping("/newRequest")
    public boolean newRequest(@RequestParam String senderUsername,@RequestParam String receiverUsername){
        return friendsService.newRequest(senderUsername,receiverUsername);
    }
    @GetMapping("/getRequests")
    public List<FriendRequest> getRequests(@RequestParam String senderUsername){
        List<FriendRequest> friendRequestList = new ArrayList<>();
        friendRequestList = friendsService.getRequests(senderUsername);
        if(!friendRequestList.isEmpty()){
            return friendRequestList;
        }
        return null;
    }
    @GetMapping("/getFriends")
    public List<Friend> getFriends(@RequestParam String username){
        List<Friend> friendList = new ArrayList<>();
        friendList = friendsService.getFriends(username);
        if(!friendList.isEmpty()){
            return friendList;
        }
        return null;
    }
    @PutMapping("/modifyRequest")
    public boolean modifyRequest(@RequestParam String senderUsername,@RequestParam String receiverUsername, @RequestParam String state){
        return friendsService.modifyRequest(senderUsername,receiverUsername,state);
    }

}
