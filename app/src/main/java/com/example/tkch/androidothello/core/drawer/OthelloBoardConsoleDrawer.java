package com.example.tkch.androidothello.core.drawer;

import com.example.tkch.androidothello.core.OthelloDisc;
import com.example.tkch.androidothello.core.drawer.OthelloBoardDrawable;

/**
 * Created by taka on 2016/06/18.
 */
public class OthelloBoardConsoleDrawer {
    public static int BLACK = OthelloDisc.BLACK;
    public static int WHITE = OthelloDisc.WHITE;
    public static int NONE = OthelloDisc.NONE;

    static String CELL_NONE = "　";
    static String CELL_BLACK = "Ｂ";
    static String CELL_WHITE = "Ｗ";
    static String CELL_CANPUT = "[]";

    static boolean DEFAULT_SHOWCANPUT = true;

    private OthelloBoardDrawable board;
    private boolean showCanPut;

    public OthelloBoardConsoleDrawer(OthelloBoardDrawable b0){
        setBoard(b0);
        showCanPut = DEFAULT_SHOWCANPUT;
    }

    public void setBoard(OthelloBoardDrawable b0){
        board = b0;
    }

    public OthelloBoardDrawable getBoard(){
        return board;
    }

    public void setShowCanPut(boolean s0){
        showCanPut = s0;
    }

    String getCellString(int x, int y, int cpcol){
        int code = board.get(x, y);
        switch( code ){
            case OthelloBoardDrawable.BLACK:
                return CELL_BLACK;
            case OthelloBoardDrawable.WHITE:
                return CELL_WHITE;
        }
        if( OthelloDisc.isDisc(cpcol) && showCanPut ){
            if( board.canPut(x, y, cpcol) ){
                return CELL_CANPUT;
            }
        }
        return CELL_NONE;
    }

    public String generateString(int cpcol){
        StringBuffer buffer = new StringBuffer();

        //head
        buffer.append("   |");
        for(int i=0; i<board.getColumnCount(); i++){
            buffer.append(String.format(" %2d", i));
        }
        buffer.append("\n---|");
        for(int i=0; i<board.getColumnCount(); i++){
            buffer.append("---");
        }
        buffer.append("\n");

        //boay
        for(int i=0; i<board.getRowCount(); i++){
            buffer.append(String.format("%2d |"));
            for(int j=0; j<board.getColumnCount(); j++){
                buffer.append(" " + getCellString(j, i, cpcol));
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public String generateString(){
        return generateString(NONE);
    }

    public void printBoard(int cpcol){
        System.out.println(generateString(cpcol));
    }

    public void printBoard(){
        System.out.println(generateString());
    }
}
