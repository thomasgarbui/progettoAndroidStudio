package com.example.demo;

public class Match {
    private Integer id;
    private String playerOneUsername;
    private String playerTwoUsername;
    private String playerOneMove;
    private String playerTwoMove;
    private String winnerUsername;
    public Match(Integer id, String playerOneUsername, String playerTwoUsername, String playerOneMove, String playerTwoMove, String winnerUsername){
        this.id = id;
        this.playerOneUsername = playerOneUsername;
        this.playerTwoUsername = playerTwoUsername;
        this.playerOneMove = playerOneMove;
        this.playerTwoMove = playerTwoMove;
        this.winnerUsername = winnerUsername;
    }
    public Integer getId(){
        return id;
    }
    public String getPlayerOneMove(){
        return playerOneMove;
    }
    public String getPlayerTwoMove(){
        return playerTwoMove;
    }
    public String getPlayerOneUsername(){
        return playerOneUsername;
    }
    public String getPlayerTwoUsername(){
        return playerTwoUsername;
    }
    public String getWinnerUsername(){
        return winnerUsername;
    }
}
