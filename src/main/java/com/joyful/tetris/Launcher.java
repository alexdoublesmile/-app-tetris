package com.joyful.tetris;

import com.joyful.tetris.audio.AudioPlayer;
import static com.joyful.tetris.audio.AudioType.CLEAR_FOUR_LINES;
import static com.joyful.tetris.audio.AudioType.CLEAR_LINE;
import static com.joyful.tetris.audio.AudioType.EXPERIENCED;
import static com.joyful.tetris.audio.AudioType.EXPERT;
import static com.joyful.tetris.audio.AudioType.FAST_DROP;
import static com.joyful.tetris.audio.AudioType.GAME_OVER;
import static com.joyful.tetris.audio.AudioType.MASTER;
import static com.joyful.tetris.audio.AudioType.PAUSE;
import static com.joyful.tetris.audio.AudioType.SELECT;
import static com.joyful.tetris.audio.AudioType.SENIOR;
import static com.joyful.tetris.audio.AudioType.SILENT_DROP;
import static com.joyful.tetris.audio.AudioType.START_GAME;
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
//        playStartGame();
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
            playStartMenu();
        });
    }

    public static void gameOver(int score) {
        playGameOver();
        
        String playerName = JOptionPane.showInputDialog( "Game Over!\nPlease enter your name.");
        leaderboardForm.addPlayer(playerName, score);
        gameForm.setVisible(false);
    }

    public static void playClearLine() {
        audioPlayer.singlePlay(CLEAR_LINE);
    }

    public static void playGameOver() {
        audioPlayer.singlePlay(GAME_OVER);
    }

    public static void playStartMenu() {
//        audioPlayer.loopPlay(START_MENU);
    }

    public static void playSelecet() {
        audioPlayer.loopPlay(SELECT);
    }

    public static void playStartGame() {
        audioPlayer.singlePlay(START_GAME);
    }

    public static void playSilentDrop() {
        audioPlayer.singlePlay(SILENT_DROP);
    }

    public static void playFastDrop() {
        audioPlayer.singlePlay(FAST_DROP);
    }

    public static void playExpert() {
        audioPlayer.singlePlay(EXPERT);
    }

    public static void playExperienced() {
        audioPlayer.singlePlay(EXPERIENCED);
    }

    public static void playMaster() {
        audioPlayer.singlePlay(MASTER);
    }

    public static void playSenior() {
        audioPlayer.singlePlay(SENIOR);
    }

    public static void playPause() {
        audioPlayer.singlePlay(PAUSE);
    }

    public static void playClearFourLines() {
        audioPlayer.singlePlay(CLEAR_FOUR_LINES);
    }
}
