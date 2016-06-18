package com.example.tkch.androidothello.core;

import com.example.tkch.androidothello.core.ai.OthelloAi;
import com.example.tkch.androidothello.core.ai.OthelloRandomAi;
import com.example.tkch.androidothello.core.drawer.OthelloBoardConsoleDrawer;

import java.util.Scanner;

/**
 * Created by taka on 2016/06/18.
 */
public class testOthelloGame {
    OthelloGameManager manager;
    OthelloBoardConsoleDrawer drawer;

    OthelloAi blackAi;
    OthelloAi whiteAi;

    void init(){
        manager = new OthelloGameManager(new OthelloBoard());
        manager.init();
        drawer = new OthelloBoardConsoleDrawer(manager.getBoard());
        int rand = (int)(Math.random() * 2);
        blackAi = (rand % 2 == 0) ?
                new OthelloRandomAi(manager.getBoard(), OthelloDisc.BLACK) : null;
        whiteAi = (rand % 2 == 0) ?
                null : new OthelloRandomAi(manager.getBoard(), OthelloDisc.WHITE);
    }

    boolean gameMain(){
        while( manager.isGameover() ){
            OthelloAi ai = (manager.nextDiscColor() == OthelloDisc.BLACK) ?
                    blackAi : whiteAi;

            if( ai != null ){
                int result = manager.put(ai);
                System.out.println("return: " + result);
            }else{
                if( manager.canPutByColor(manager.nextDiscColor()) ){
                    Scanner scanner = new Scanner(System.in);

            }
        }
    }

    public static void main(String[] args){
    }


}
