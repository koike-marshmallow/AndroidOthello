package com.example.tkch.androidothello.core;

import com.example.tkch.androidothello.core.drawer.OthelloBoardDrawable;

/**
 * Created by taka on 2016/06/17.
 */
public class OthelloBoard implements OthelloBoardDrawable {

    public static final int PUT_SUCCESS = 100;
    public static final int PUT_OUTOFBOUNDS = 101;
    public static final int PUT_NOFLIP = 102;
    public static final int PUT_INVALIDDISC = 103;
    public static final int PUT_RESERVED = 104;

    public static int DEFAULT_ROWS = 8;
    public static int DEFAULT_COLUMNS = 8;
    public static boolean DEFAULT_AUTOFLIP_CONFIG = true;

    static int[] VEC_X = {-1,  0,  1,  1,  1,  0, -1, -1};
    static int[] VEC_Y = {-1, -1, -1,  0,  1,  1,  1,  0};

    private int[][] cells;
    private boolean autoFlip;

    public OthelloBoard(int rows, int cols){
        autoFlip = DEFAULT_AUTOFLIP_CONFIG;
        init(rows, cols);
    }

    public OthelloBoard(){
        this(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    void init(int rows, int cols){
        cells = new int[rows][cols];
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[i].length; j++){
                cells[i][j] = NONE;
            }
        }
    }

    public boolean inBound(int x, int y){
        if( y >= 0 && y < cells.length ){
            if( x >= 0 && x < cells[y].length ){
                return true;
            }
        }
        return false;
    }

    public int get(int x, int y){
        return inBound(x, y) ? cells[y][x] : NONE;
    }

    boolean willFlipLine(int x, int y, int dx, int dy, int color) {
        int tx = x + dx;
        int ty = y + dy;
        boolean flag = false;
        while( inBound(tx, ty) ){
            int tmp = get(tx, ty);
            if( tmp == NONE ){
                return false;
            }else if( tmp == color ){
                return flag;
            }else{
                flag = true;
            }
            tx += dx;
            ty += dy;
        }
        return false;
    }

    int flipLine(int x, int y, int dx, int dy, int color){
        int cnt = 0;
        if( willFlipLine(x, y, dx, dy, color) ){
            int tx = x + dx;
            int ty = y + dy;
            while( inBound(tx, ty) ){
                int tmp = get(tx, ty);
                if( tmp != NONE && tmp != color ){
                    cells[ty][tx] = color;
                    cnt++;
                }else{
                    break;
                }
                tx += dx;
                ty += dy;
            }
        }
        return cnt;
    }

    public boolean willFlip(int x, int y, int color){
        for(int i=0; i<Math.min(VEC_X.length, VEC_Y.length); i++){
            if( willFlipLine(x, y, VEC_X[i], VEC_Y[i], color)){
                return true;
            }
        }
        return false;
    }

    public int flip(int x, int y, int color){
        int cnt = 0;
        for(int i=0; i<Math.min(VEC_X.length, VEC_Y.length); i++){
            cnt += flipLine(x, y, VEC_X[i], VEC_Y[i], color);
        }
        return cnt;
    }

    public void set(int x, int y, int val){
        if( inBound(x, y) ){
            cells[y][x] = val;
        }
    }

    public boolean canPut(int x, int y, int color){
        return color != NONE && inBound(x, y) && willFlip(x, y, color) &&
                get(x, y) == NONE;
    }

    public int put(int x, int y, int disc, boolean sAutoFlip){
        if( disc == NONE ) return PUT_INVALIDDISC;
        if( inBound(x, y) ) {
            int tmp = get(x, y);
            if( tmp == NONE ){
                if( willFlip(x, y, disc) ){
                    cells[y][x] = disc;
                }else{
                    return PUT_NOFLIP;
                }
            }else{
                return PUT_RESERVED;
            }
        }else{
            return PUT_OUTOFBOUNDS;
        }

        if( sAutoFlip ){
            flip(x, y, disc);
        }
        return PUT_SUCCESS;
    }

    public int put(int x, int y, int disc){
        return put(x, y, disc, autoFlip);
    }

    public int countDiscs(int disc){
        int cnt = 0;
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[i].length; j++){
                if( cells[i][j] == disc ) cnt++;
            }
        }
        return cnt;
    }

    public int getRowCount(){ return cells.length; }
    public int getColumnCount(){ return (cells.length > 0 ? cells[0].length : 0); }

    public String toString(){
        String hash = super.toString();
        int b = countDiscs(BLACK);
        int w = countDiscs(WHITE);
        int n = countDiscs(NONE);
        return String.format("%s: (%dx%d)[N:%d, B:%d, W:%d]",
                hash, getRowCount(), getColumnCount(), n, b, w);
    }
}
