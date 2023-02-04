package com.joyful.tetris.audio;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class AudioPlayer {
    private static final Map<AudioType, Clip> clipMap = AudioType.mapAllTypesToClip();
    private static final Map<AudioType, AudioInputStream> streamMap = AudioType.mapAllTypesToStream();

    private Map<String, Clip> currentClipMap = new HashMap<>();

    private Clip mainThemeClip;

    public void singlePlay(AudioType soundType) {
        Clip soundClip = clipMap.get(soundType);
        soundClip.setFramePosition(0);
        soundClip.start();
    }

    public void loopPlay(AudioType soundType) {
        AudioInputStream stream = streamMap.get(soundType);
        AudioFormat format = stream.getFormat();

        try {
            mainThemeClip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Line line = AudioSystem.getLine(info);

            if (!line.isOpen()) {
                mainThemeClip.open(stream);
                mainThemeClip.loop(Clip.LOOP_CONTINUOUSLY);
                mainThemeClip.start();
            }
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopMainTheme() {
        mainThemeClip.close();
    }

    public void disableMainTheme() {
        final FloatControl control = (FloatControl) mainThemeClip.getControl(MASTER_GAIN);
        control.setValue(-80.0f);
    }

    public void enableMainTheme() {
        final FloatControl control = (FloatControl) mainThemeClip.getControl(MASTER_GAIN);
        control.setValue(6.0f);
    }

    public void disableSounds() {
        clipMap.values().stream()
                .forEach(clip -> {
                    final FloatControl control = (FloatControl) clip.getControl(MASTER_GAIN);
                    control.setValue(-80.0f);
                });
    }

    public void enableSounds() {
        clipMap.values().stream()
                .forEach(clip -> {
                    final FloatControl control = (FloatControl) clip.getControl(MASTER_GAIN);
                    control.setValue(6.0f);
                });
    }
}
