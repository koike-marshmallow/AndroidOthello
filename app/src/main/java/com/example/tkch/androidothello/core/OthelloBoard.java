package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/17.
 */
public class OthelloBoard {
    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    public static final int SET_SUCCESS = 100;
    public static final int SET_OUTOFBOUNDS = 101;
    public static final int SET_NOFLIP = 102;
    public static final int SET_INVALIDDISC = 103;
    public static final int SET_RESERVED = 104;

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

    public int put(int x, int y, int disc, boolean sAutoFlip){
        if( disc == NONE ) return SET_INVALIDDISC;
        if( inBound(x, y) ) {
            int tmp = get(x, y);
            if( tmp == NONE ){
                if( willFlip(x, y, disc) ){
                    cells[y][x] = disc;
                }else{
                    return SET_NOFLIP;
                }
            }else{
                return SET_RESERVED;
            }
        }else{
            return SET_OUTOFBOUNDS;
        }

        if( sAutoFlip ){
            flip(x, y, disc);
        }
        return SET_SUCCESS;
    }

    public int put(int x, int y, int disc){
        return put(x, y, disc, autoFlip);
    }

    public int getRowCount(){ return cells.length; }
    public int getColumnCount(){ return (cells.length > 0 ? cells[0].length : 0); }
}
