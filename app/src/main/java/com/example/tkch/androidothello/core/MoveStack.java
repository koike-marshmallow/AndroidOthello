package com.example.tkch.androidothello.core;

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

        private int type;
        private XYPoint position;
        private int prevDisc;
        private int setDisc;

        public MoveRecord(int tp0, XYPoint pt0, int pd0, int sd0){
            type = tp0;
            position = pt0;
            prevDisc = pd0;
            setDisc = sd0;
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
            return String.format("%s: [TYPE:%d, POS:(%d, %d), PREV:%d, SET:%d]",
                    super.toString(), type, position.getX(), position.getY(), prevDisc, setDisc);
        }
    }
}
