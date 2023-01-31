package com.joyful.tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GameArea gameArea;
    
    public GameThread(GameArea gameArea) {
        this.gameArea = gameArea;
        
    }

    @Override
    public void run() {
        while(true) {   
            gameArea.moveBlockDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
