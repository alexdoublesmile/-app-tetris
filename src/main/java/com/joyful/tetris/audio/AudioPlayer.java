package com.joyful.tetris.audio;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private static final String SOUND_DIR_PATH = "sound";
    private static final String GAME_OVER_SOUND_FILE = SOUND_DIR_PATH + File.separator + "success.wav";
    private static final String CLEAR_LINE_SOUND_PATH = SOUND_DIR_PATH + File.separator + "clear.wav";

    private Clip gameOverClip;
    private Clip clearLineClip;
    
    public AudioPlayer() {
        try {
            gameOverClip = AudioSystem.getClip();
            clearLineClip = AudioSystem.getClip();
            gameOverClip.open(AudioSystem.getAudioInputStream(new File(GAME_OVER_SOUND_FILE).getAbsoluteFile()));
            clearLineClip.open(AudioSystem.getAudioInputStream(new File(CLEAR_LINE_SOUND_PATH).getAbsoluteFile()));
            
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearLine() {
        clearLineClip.setFramePosition(0);
        clearLineClip.start();
    }

    public void gameOver() {
        gameOverClip.setFramePosition(0);
        gameOverClip.start();
    }
}
