package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public class OthelloGameManager {
    public static final int BLACK = OthelloDisc.BLACK;
    public static final int WHITE = OthelloDisc.WHITE;

    public static final int PUT_SUCCESS = 201;
    public static final int PUT_

    OthelloBoard board;
    boolean inBlackTurn;


    public void init(){
        board = new OthelloBoard();
        board.set(3, 3, BLACK);
        board.set(4, 4, BLACK);
        board.set(3, 4, WHITE);
        board.set(4, 3, WHITE);
    }

    public int nextDiscColor(){
        return inBlackTurn ? BLACK : WHITE;
    }

    public int put(int x, int y, int color){

    }
}
