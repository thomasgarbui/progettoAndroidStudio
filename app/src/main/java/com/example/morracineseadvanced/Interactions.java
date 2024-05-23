package com.example.morracineseadvanced;

public class Interactions {
    public boolean[][] matrice;
    public String[] moves;

    public Interactions(){
        this.matrice = new boolean[][]{
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

        this.moves = new String[]{
            "rock","fire","scissors","snake","human","tree","wolf","sponge","paper","air","water","dragon","devil","lightning","gun"
        };
    }
}
