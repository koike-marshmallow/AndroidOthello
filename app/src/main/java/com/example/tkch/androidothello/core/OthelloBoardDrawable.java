package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public interface OthelloBoardDrawable {
    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    public boolean canPut(int x, int y, int color);
    public int get(int x, int y);
    public int countDiscs(int disc);
    public int getRowCount();
    public int getColumnCount();
}
