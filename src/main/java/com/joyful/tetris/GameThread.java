package com.joyful.tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private GameForm gameForm;
    private GameArea gameArea;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    private int pause = 1000;
    private int speedupPerLevel = 200;
    
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
                    Thread.sleep(pause);
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
            
            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                gameForm.updateLevel(level);
                pause -= speedupPerLevel;
            }
        }
    }
    
}
