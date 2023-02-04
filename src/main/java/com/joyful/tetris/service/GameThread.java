package com.joyful.tetris.service;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.PlayerInfo;
import com.joyful.tetris.model.PlayerRank;
import static com.joyful.tetris.model.PlayerRank.NOOB;
import com.joyful.tetris.util.AudioHelper;
import com.joyful.tetris.util.ScoreHelper;
import static com.joyful.tetris.util.ScoreHelper.getPercentByDouble;
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
    private long blockPause = 1000;
    private boolean gamePaused;
    private double speedupPerLevel = 0.1;
    
    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameForm = gameForm;
        this.gameArea = gameArea;
        
        gameForm.updateScore(score);
        gameForm.updateLines(lines);
        gameForm.updateLevel(level);
        gameForm.updateRank(rank);
        gameForm.updateSpeed(speed);
        gameForm.updateEfficiency(efficiency);

        gameArea.resetRank();
        gameArea.resetBlocks();
    }

    @Override
    public void run() {
        long startTime = nanoTime();
        long startPauseTime = 0;
        long pauseTime = 0;
        while(true) {   
            gameArea.spawnBlock();
            
            while(gamePaused || gameArea.needMoveBlockDown()) {
                if (gamePaused && startPauseTime == 0) {
                    startPauseTime = nanoTime();
                } else {
                    if (!gamePaused && startPauseTime > 0) {
                        pauseTime += (nanoTime() - startPauseTime);
                        startPauseTime = 0;
                    }
                }

                try {
                    Thread.sleep(blockPause);
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
                        .speed(getPercentByDouble(speed))
                        .efficiency(getPercentByDouble(efficiency))
                        .build());
                break;
            }
            gameArea.moveBlockToBackground();
            blocksNumber++;

            startTime += pauseTime;
            pauseTime = 0;
            speed = blocksNumber / (getSeconds(nanoTime() - startTime));

            int clearedLines = gameArea.clearLines();
            score += ScoreHelper.getScore(clearedLines);
            lines += clearedLines;

            if (lines > 0) {
                efficiency = (double) lines / blocksNumber;
            }

            rank = rank.getRankByScore(score);
            
            gameArea.updateRank(rank);
            gameForm.updateRank(rank);
            gameForm.updateScore(score);
            gameForm.updateLines(lines);
            gameForm.updateSpeed(speed);
            gameForm.updateEfficiency(efficiency);
            
            AudioHelper.playSoundByLinesNumber(clearedLines);
            
            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                gameForm.updateLevel(level);
                blockPause = (long) (blockPause - (blockPause * speedupPerLevel));
            }
        }
    }

    public boolean togglePause() {
        gamePaused = !gamePaused;
        return gamePaused;
    }
}
