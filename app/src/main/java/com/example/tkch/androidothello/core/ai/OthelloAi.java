package com.example.tkch.androidothello.core.ai;

import com.example.tkch.androidothello.core.XYPoint;

/**
 * Created by taka on 2016/06/18.
 */
public abstract class OthelloAi {
    public interface OnMessageChangedListener {
        void onMessageChanged(String msg);
    }

    public class Movement{
        public int code;
        public XYPoint position;

        Movement(int c0, XYPoint p0){
            code = c0;
            position = p0;
        }

        public int getCode(){
            return code;
        }

        public XYPoint getPosition(){
            return position;
        }
    }

    public static final int AIPUT_CONTINUE = 201;
    public static final int AIPUT_PASSED = 202;
    public static final int AIPUT_ERROR = 203;

    protected String message;
    protected OnMessageChangedListener msgListener;
    protected int color;

    public OthelloAi(){
        message = "init";
        msgListener = null;
        color = 0;
    }

    public String getMessage(){
        return message;
    }

    protected void talk(String msg){
        message = msg;
        if( msgListener != null ){
            msgListener.onMessageChanged(message);
        }
    }

    public void setMessageChangedListener(OnMessageChangedListener listener){
        msgListener = listener;
    }

    public void setColor(int c0){
        color = c0;
    }

    public int getColor(){
        return color;
    }

    abstract public Movement nextMove();
}
