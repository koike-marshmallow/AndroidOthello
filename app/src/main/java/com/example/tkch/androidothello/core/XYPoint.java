package com.example.tkch.androidothello.core;

/**
 * Created by taka on 2016/06/18.
 */
public class XYPoint {
    int x;
    int y;

    public XYPoint(int x0, int y0){
        x = x0;
        y = y0;
    }

    public XYPoint(){
        this(0, 0);
    }

    public void setX(int x0){
        x = x0;
    }

    public int getX(){
        return x;
    }

    public void setY(int y0){
        y = y0;
    }

    public int getY(){
        return y;
    }

    public void set(int x0, int y0){
        setX(x0);
        setY(y0);
    }

    public void translate(int dx, int dy){
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public String toString(){
        return String.format("%s (%d, %d)",
                super.toString(), getX(), getY());
    }
}
