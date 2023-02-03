package com.joyful.tetris.util;

import com.joyful.tetris.Launcher;
import static com.joyful.tetris.audio.AudioConstants.SOUND_DIR_PATH;
import com.joyful.tetris.audio.AudioPlayer;
import static com.joyful.tetris.model.PlayerRank.EXPERIENCED;
import static com.joyful.tetris.model.PlayerRank.EXPERT;
import static com.joyful.tetris.model.PlayerRank.MASTER;
import static com.joyful.tetris.model.PlayerRank.NOOB;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class AudioHelper {

    public static Clip getClip(String fileName) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(
                    new File(SOUND_DIR_PATH + File.separator + fileName).getAbsoluteFile()));

        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clip;
    }

    public static AudioInputStream getStream(String fileName) {
        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(new File(SOUND_DIR_PATH + File.separator + fileName).getAbsoluteFile());

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stream;
    }

    public static void playSoundByLinesNumber(int clearedLines) {
        switch (clearedLines) {
            case 1, 2, 3 -> Launcher.playClearLine();
            case 4 -> Launcher.playClearFourLines();
        }
    }

    public static void playSoundByScore(int previousScore, int clearedLines) {
        int currentScore = previousScore + ScoreHelper.getScore(clearedLines);
        if (previousScore < NOOB.getNextRankScore() && currentScore >= NOOB.getNextRankScore()) {
            Launcher.playExperienced();
        }
        if (previousScore < EXPERIENCED.getNextRankScore() && currentScore >= EXPERIENCED.getNextRankScore()) {
            Launcher.playExpert();
        }
        if (previousScore < EXPERT.getNextRankScore() && currentScore >= EXPERT.getNextRankScore()) {
            Launcher.playMaster();
        }
        if (previousScore < MASTER.getNextRankScore() && currentScore >= MASTER.getNextRankScore()) {
            Launcher.playSenior();
        }
    }
}
