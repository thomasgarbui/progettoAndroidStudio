package com.example.morracineseadvanced;

public class Interactions {
    private boolean[][] matrice = {

    };

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
    }
}
