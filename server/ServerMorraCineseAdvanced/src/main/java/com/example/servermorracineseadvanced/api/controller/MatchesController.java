package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchesController {
    private MatchesService matchesService;
    @Autowired
    public MatchesController(MatchesService matchesService){
        this.matchesService = matchesService;
    }

    @PostMapping("/newMatch")
    public boolean newMatch(@RequestParam String playerOneUsername){
        return matchesService.newMatch(playerOneUsername);
    }
    @PutMapping("/selectMove")
    public boolean selectMove(@RequestParam String playerUsername,@RequestParam String move, @RequestParam String playerNumber){
        return matchesService.selectMove(playerUsername,move,playerNumber);
    }
    @PutMapping("/joinMatch")
    public boolean joinMatch(@RequestParam String playerUsername,@RequestParam Integer id){
        return matchesService.joinMatch(playerUsername,id);
    }
    @GetMapping("/getMatches")
    public List<Match> getMatches(@RequestParam String playerUsername){
        List<Match> matchList = new ArrayList<>();
        matchList = matchesService.getMatches(playerUsername);
        if(!matchList.isEmpty()){
            return matchList;
        }
        return null;
    }

}
