package com.joyful.tetris.service;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.PlayerInfo;
import com.joyful.tetris.model.PlayerRank;
import static com.joyful.tetris.model.PlayerRank.NOOB;
import com.joyful.tetris.util.AudioHelper;
import com.joyful.tetris.util.ScoreHelper;
import static com.joyful.tetris.util.TimeHelper.getSeconds;
import com.joyful.tetris.view.GameForm;
import com.joyful.tetris.view.panel.GameArea;
import static java.lang.System.nanoTime;

public class GameThread extends Thread {
    private GameForm gameForm;
    private GameArea gameArea;
    
    private PlayerRank rank = NOOB;
    private int score;
    private int lines;
    private int blocksNumber;
    private int level = 1; 
    private double speed;
    private double efficiency;
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
        long startTime = nanoTime();
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
                Launcher.gameOver(PlayerInfo.builder()
                        .score(score)
                        .lines(lines)
                        .rank(rank.getTitle())
                        .level(level)
                        .speed(speed)
                        .efficiency(efficiency)
                        .build());
                break;
            }
            gameArea.moveBlockToBackground();
            blocksNumber++;
            speed =  getSeconds(nanoTime() - startTime) / blocksNumber;
            
            int clearedLines = gameArea.clearLines();
            score += ScoreHelper.getScore(clearedLines);
            lines += clearedLines;

            if (lines > 0) {
                efficiency = blocksNumber / lines;
            }

            rank = rank.getRankByScore(score);
            
            gameForm.updateRank(rank);
            gameForm.updateScore(score);
            gameForm.updateLines(lines);
            gameForm.updateSpeed(speed);
            gameForm.updateEfficiency(efficiency);
            
            AudioHelper.playSoundByLinesNumber(clearedLines);
            AudioHelper.playSoundByScore(score, clearedLines);
            
            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                gameForm.updateLevel(level);
                pause = (long) (pause - (pause * speedupPerLevel));
            }
        }
    }
}
