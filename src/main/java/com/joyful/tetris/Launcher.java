package com.joyful.tetris;

import com.joyful.tetris.util.AudioPlayer;
import com.joyful.tetris.view.StartupForm;
import com.joyful.tetris.view.LeaderboardForm;
import com.joyful.tetris.view.GameForm;
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
            gameForm = new GameForm();
            leaderboardForm = new LeaderboardForm();
            startupForm = new StartupForm();
            audioPlayer = new AudioPlayer();
            
            startupForm.setVisible(true);
        });
    }

    public static void gameOver(int score) {
        playGameOver();
        
        String playerName = JOptionPane.showInputDialog( "Game Over!\nPlease enter your name.");
        gameForm.setVisible(false);
        leaderboardForm.addPlayer(playerName, score);
    }

    public static void playClearLine() {
        audioPlayer.clearLine();
    }

    public static void playGameOver() {
        audioPlayer.gameOver();
    }
}
