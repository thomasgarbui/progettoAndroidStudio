package com.example.servermorracineseadvanced.api.model;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

public class Matches {
    private Integer id;
    private String player1Id;
    private String player2Id;
    private String player1Move;
    private String player2Move;
    private String winnerId;
    public Matches(Integer id,String player1Id,String player2Id,String player1Move,String player2Move,String winnerId){
        this.id = id;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.player1Move = player1Move;
        this.player2Move = player2Move;
        this.winnerId = winnerId;
    }
    public Integer getId(){
        return id;
    }
    public String getPlayer1Move(){
        return player1Move;
    }
    public String getPlayer2Move(){
        return player2Move;
    }
    public String getPlayer1Id(){
        return player1Id;
    }
    public String getPlayer2Id(){
        return player2Id;
    }
    public String getWinnerId(){
        return winnerId;
    }
}
