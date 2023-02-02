package com.joyful.tetris;

import com.joyful.tetris.audio.AudioPlayer;
import static com.joyful.tetris.audio.AudioType.CLEAR_LINE;
import static com.joyful.tetris.audio.AudioType.GAME_OVER;
import com.joyful.tetris.view.GameForm;
import com.joyful.tetris.view.LeaderboardForm;
import com.joyful.tetris.view.StartupForm;
import javax.swing.JOptionPane;

public class Launcher {
    private static GameForm gameForm;
    private static LeaderboardForm leaderboardForm;
    private static StartupForm startupForm;
    private static AudioPlayer audioPlayer;
    
    public static void start() {
        gameForm.setVisible(true);
        gameForm.startGame();
    }

    public static void showLeaderboard() {
        leaderboardForm.setVisible(true);
    }

    public static void showStartup() {
        startupForm.setVisible(true);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            gameForm = new GameForm("Tetris Pro");
            leaderboardForm = new LeaderboardForm("Tetris Leaderboard");
            startupForm = new StartupForm("Tetris Pro");
            audioPlayer = new AudioPlayer();
            
            startupForm.setVisible(true);
        });
    }

    public static void gameOver(int score) {
        playGameOver();
        
        String playerName = JOptionPane.showInputDialog( "Game Over!\nPlease enter your name.");
        leaderboardForm.addPlayer(playerName, score);
        gameForm.setVisible(false);
    }

    public static void playClearLine() {
        audioPlayer.play(CLEAR_LINE);
    }

    public static void playGameOver() {
        audioPlayer.play(GAME_OVER);
    }
}
