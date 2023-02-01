package com.joyful.tetris;

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
}
