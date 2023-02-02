package com.joyful.tetris.service;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.util.AudioHelper;
import com.joyful.tetris.util.ScoreHelper;
import com.joyful.tetris.view.GameForm;
import com.joyful.tetris.view.panel.GameArea;

public class GameThread extends Thread {
    private GameForm gameForm;
    private GameArea gameArea;
    private int score;
    private int level = 1;
    private int scorePerLevel = 1000;
    private long pause = 1000;
    private double speedupPerLevel = 0.1;
    
    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameForm = gameForm;
        this.gameArea = gameArea;
        
        gameForm.updateScore(score);
        gameForm.updateLevel(level);
    }

    @Override
    public void run() {
        while(true) {   
            gameArea.spawnBlock();
            
            while(gameArea.moveBlockDown()) {   
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    return;
                }
            }
            
            if (gameArea.isBlockOutOfBounds()) {
                Launcher.gameOver(score);
                break;
            }
            gameArea.moveBlockToBackground();
            int clearedLines = gameArea.clearLines();
            AudioHelper.playSoundByLinesNumber(clearedLines);
            AudioHelper.playSoundByScore(score, clearedLines);
            score += ScoreHelper.getScore(clearedLines);
            gameForm.updateScore(score);
            
            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                gameForm.updateLevel(level);
                pause = (long) (pause - (pause * speedupPerLevel));
            }
        }
    }
}
