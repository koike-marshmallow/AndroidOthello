package com.example.tkch.androidothello.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taka on 2016/06/18.
 */
public class OthelloRandomAi extends OthelloAi{
    static String TALK_INIT = "よろしくおねがいします";
    static String TALK_PASS = "パスです";
    static String TALK_CONTINUE = "置きました";
    static String TALK_ERROR = "OthelloAiでエラーが発生しました";

    private OthelloBoard board;

    public OthelloRandomAi(OthelloBoard b0, int c0){
        board = b0;
        setColor(c0);
        talk(TALK_INIT);
    }

    List<XYPoint> getCanPutPoints(){
        List<XYPoint> list = new ArrayList<XYPoint>();
        for(int i=0; i<board.getRowCount(); i++){
            for(int j=0; j<board.getColumnCount(); j++){
                if( board.canPut(j, i, color)){
                    list.add(new XYPoint(j, i));
                }
            }
        }
        return list;
    }

    public Movement nextMove(){
        List<XYPoint> points = getCanPutPoints();
        if( points.size() > 0 ){
            talk(TALK_CONTINUE);
            return new Movement(AIPUT_CONTINUE,
                    points.get((int)(Math.random() * points.size())));
        }
        talk(TALK_PASS);
        return new Movement(AIPUT_PASSED, null);
    }
}
