package com.example.tkch.androidothello.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taka on 2016/06/18.
 */
public class MoveStack {
    public class MoveRecord{
        public static final int NONE = OthelloDisc.NONE;
        public static final int BLACK = OthelloDisc.BLACK;
        public static final int WHITE = OthelloDisc.WHITE;

        public static final int PUT = 201;
        public static final int SET = 202;
        public static final int FLP = 203;
        public static final int OTHERS = 291;

        private int id;
        private int gid;

        private int type;
        private XYPoint position;
        private int prevDisc;
        private int setDisc;

        public MoveRecord(int id0, int gi0, int tp0, XYPoint pt0, int pd0, int sd0){
            id = id0;
            gid = gi0;
            type = tp0;
            position = pt0;
            prevDisc = pd0;
            setDisc = sd0;
        }

        public int getId(){
            return id;
        }

        public int getGroupId(){
            return gid;
        }

        public int getType(){
            return type;
        }

        public XYPoint getPosition(){
            return position;
        }

        public int getPreviousDisc(){
            return prevDisc;
        }

        public int getSetDisc(){
            return setDisc;
        }

        public String toString(){
            return String.format("%s: [ID:(%d(%d)), TYPE:%d, POS:(%d, %d), PREV:%d, SET:%d]",
                    super.toString(), id, gid, type,
                    position.getX(), position.getY(), prevDisc, setDisc);
        }
    }

    public static final int NONE = OthelloDisc.NONE;
    public static final int BLACK = OthelloDisc.BLACK;
    public static final int WHITE = OthelloDisc.WHITE;

    public static final int PUT = MoveRecord.PUT;
    public static final int SET = MoveRecord.SET;
    public static final int FLP = MoveRecord.FLP;
    public static final int OTHERS = MoveRecord.OTHERS;

    private List<MoveRecord> stack;
    int gidCounter;

    public MoveStack(){
        init();
    }

    public void init(){
        stack = new ArrayList<MoveRecord>();
        gidCounter = 0;
    }

    public void push(int type, XYPoint pos, int prevDisc, int setDisc){
        stack.add(new MoveRecord(stack.size(), gidCounter, type, pos, prevDisc, setDisc));
    }

    public void push(int type, int px, int py, int prevDisc, int setDisc){
        push(type, new XYPoint(px, py), prevDisc, setDisc);
    }


}
