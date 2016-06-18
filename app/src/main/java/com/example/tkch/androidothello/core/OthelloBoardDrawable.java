package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public interface OthelloBoardDrawable {
    public static final int NONE = OthelloDisc.NONE;
    public static final int BLACK = OthelloDisc.BLACK;
    public static final int WHITE = OthelloDisc.WHITE;

    public boolean canPut(int x, int y, int color);
    public int get(int x, int y);
    public int countDiscs(int disc);
    public int getRowCount();
    public int getColumnCount();
}
