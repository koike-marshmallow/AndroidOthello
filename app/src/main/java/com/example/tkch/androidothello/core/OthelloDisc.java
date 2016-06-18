package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public class OthelloDisc {
    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    public static boolean isDisc(int val){
        switch( val ) {
            case BLACK:
            case WHITE:
                return true;
        }
        return false;
    }

    public static boolean isValid(int val){
        switch( val ){
            case NONE:
            case BLACK:
            case WHITE:
                return true;
        }
        return false;
    }
}
