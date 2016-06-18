package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public class OthelloGameManager {
    public static final int BLACK = OthelloDisc.BLACK;
    public static final int WHITE = OthelloDisc.WHITE;

    public static final int SUCCESS = 100;
    public static final int OUTOFBOUNDS = 101;
    public static final int NOFLIP = 102;
    public static final int INVALIDDISC = 103;
    public static final int RESERVED = 104;
    public static final int GAMEOVER = 105;
    public static final int GAMEOVERED = 107;
    public static final int ERROR = 106;


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

    public boolean canPutByColor(int color){
        for(int i=0; i<board.getRowCount(); i++){
            for(int j=0; j<board.getColumnCount(); j++){
                if( board.canPut(j, i, color) )return true;
            }
        }
        return false;
    }

    public boolean isGameover(){
        return !canPutByColor(BLACK) && canPutByColor(WHITE);
    }

    public int put(int x, int y, int color) {
        if( isGameover() ) return GAMEOVERED;
        if( color != nextDiscColor() ) return INVALIDDISC;

        switch( board.put(x, y, color) ){
            case OthelloBoard.PUT_SUCCESS:
                break;
            case OthelloBoard.PUT_OUTOFBOUNDS:
                return OUTOFBOUNDS;
            case OthelloBoard.PUT_NOFLIP:
                return NOFLIP;
            case OthelloBoard.PUT_INVALIDDISC:
                return INVALIDDISC;
            case OthelloBoard.PUT_RESERVED:
                return RESERVED;
            default:
                return ERROR;
        }

        inBlackTurn = !inBlackTurn;
        return isGameover() ? GAMEOVER : SUCCESS;
    }

    public int pass(int color){
        if( isGameover() ) return GAMEOVERED;
        if( color != nextDiscColor() ) return INVALIDDISC;

        inBlackTurn = !inBlackTurn;
        return isGameover() ? GAMEOVER : SUCCESS;
    }

    public int put(OthelloAi ai){
        if( isGameover() ) return GAMEOVERED;
        if( ai.getColor() != nextDiscColor() ) return INVALIDDISC;

        OthelloAi.Movement move = ai.nextMove();
        switch( move.getCode() ){
            case OthelloAi.AIPUT_CONTINUE:
                XYPoint pos = move.getPosition();
                return put(pos.getX(), pos.getY(), ai.getColor());
            case OthelloAi.AIPUT_PASSED:
                return pass(ai.getColor());
        }
        return ERROR;
    }


}
