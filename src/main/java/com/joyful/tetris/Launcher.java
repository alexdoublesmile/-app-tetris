package com.joyful.tetris;

import javax.swing.JOptionPane;

public class Launcher {
    private static GameForm gameForm;
    private static LeaderboardForm leaderboardForm;
    private static StartupForm startupForm;
    
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
            
            startupForm.setVisible(true);
        });
    }

    public static void gameOver(int score) {
        String playerName = JOptionPane.showInputDialog( "Game Over!\nPlease enter your name.");
        gameForm.setVisible(false);
        leaderboardForm.addPlayer(playerName, score);
    }
}
