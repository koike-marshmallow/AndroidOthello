package com.example.tkch.androidothello.core;

import com.example.tkch.androidothello.core.ai.OthelloAi;
import com.example.tkch.androidothello.core.ai.OthelloRandomAi;
import com.example.tkch.androidothello.core.drawer.OthelloBoardConsoleDrawer;

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
        OthelloAi ai = new OthelloRandomAi(manager.getBoard());
        blackAi = (rand % 2 == 0) ? ai : null;
        whiteAi = (rand % 2 == 0) ? null : ai;
    }

    void gameMain(){
        
    }

    public static void main(String[] args){
    }


}
