package com.joyful.tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GameForm gameForm;
    private GameArea gameArea;
    private int score;
    
    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameForm = gameForm;
        this.gameArea = gameArea;
        
    }

    @Override
    public void run() {
        while(true) {   
            gameArea.spawnBlock();
            while(gameArea.moveBlockDown()) {   
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (gameArea.isBlockOutOfBounds()) {
                break;
            }
            gameArea.moveBlockToBackground();
            score += gameArea.clearLines();
            gameForm.updateScore(score);
        }
    }
    
}
