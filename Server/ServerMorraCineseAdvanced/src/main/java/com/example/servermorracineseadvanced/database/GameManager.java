package com.example.demo;

import java.util.Arrays;
import java.util.List;

public class GameManager {
    private boolean[][] combination;
    private List<String> moves;
    public GameManager(){
        this.combination = new boolean[][]{
                {false, true, true, true, true, true, true, true, false, false, false, false, false, false, false},
                {false, false, true, true, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, false, true, true, true, true, true, true, true, false, false, false, false, false},
                {false, false, false, false, true, true, true, true, true, true, true, false, false, false, false},
                {false, false, false, false, false, true, true, true, true, true, true, true, false, false, false},
                {false, false, false, false, false, false, true, true, true, true, true, true, true, false, false},
                {false, false, false, false, false, false, false, true, true, true, true, true, true, true, false},
                {false, false, false, false, false, false, false, false, true, true, true, true, true, true, true},
                {true, false, false, false, false, false, false, false, true, true, true, true, true, true, false},
                {true, true, false, false, false, false, false, false, true, true, true, true, true, false, false},
                {true, true, true, false, false, false, false, false, true, true, true, true, false, false, false},
                {true, true, true, true, false, false, false, false, true, true, true, false, false, false, false},
                {true, true, true, true, true, false, false, false, true, true, false, false, false, false, false},
                {true, true, true, true, true, true, false, false, true, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, false, false, false, false, false, false, false, false}
        };

        this.moves = Arrays.stream(new String[]{
                "rock","fire","scissors","snake","human","tree","wolf","sponge","paper","air","water","dragon","devil","lightning","gun"
        }).toList();
    }
    public boolean isMoveValid(String move){
        return moves.contains(move);
    }

    public Integer whoWon(Match match){
        Integer winner = -1;
        if(!match.getPlayerOneMove().equals(match.getPlayerTwoMove())){
            if(combination[moves.indexOf(match.getPlayerOneMove())][moves.indexOf(match.getPlayerTwoMove())]){
                winner = 1;
            }else{
                winner = 2;
            }
        }
        return winner;
    }
}
