package com.example.demo;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchesService {
    private DataManager dataManager;
    public MatchesService(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public boolean newMatch(String playerOneUsername){
        boolean result = false;
        if(!playerOneUsername.isEmpty()){
            result = dataManager.newMatch(playerOneUsername);
        }
        return result;
    }
    public boolean selectMove(String playerUsername,String move,String playerNumber){
        boolean result = false;
        if(!playerUsername.isEmpty() && !move.isEmpty() && !playerNumber.isEmpty()){
            result = dataManager.selectMove(playerUsername,move,playerNumber);
        }
        return result;
    }
    public boolean joinMatch(String playerUsername,Integer id){
        boolean result = false;
        if(!playerUsername.isEmpty() && id >= 0){
            result = dataManager.joinMatch(playerUsername,id);
        }
        return result;
    }
    public List<Match> getMatches(String playerUsername){
        List<Match> matchList = new ArrayList<>();
        if(!playerUsername.isEmpty()){
            matchList = dataManager.getMatches(playerUsername);
        }
        return matchList;
    }
    public boolean deleteMatch(Integer id){
        boolean result = false;
        if(id >= 0){
            result = dataManager.deleteMatch(id);
        }
        return result;
    }
}
