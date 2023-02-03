package com.joyful.tetris.audio;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;

public class AudioPlayer {    
    private static final Map<AudioType, Clip> clipMap = AudioType.mapAllTypesToClip();
    private static final Map<AudioType, AudioInputStream> streamMap = AudioType.mapAllTypesToStream();
    
    private Line mainThemeLine;
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

    public void disableSound() {
//        streamMap.values().stream()
//                .forEach(stream -> {
//                    try {
//                        AudioFormat format = stream.getFormat();
//                        DataLine.Info info = new DataLine.Info(Clip.class, format);
//                        Line line = AudioSystem.getLine(info);
//
//                        FloatControl gainControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
//                        float min = gainControl.getMinimum(); 
//                        gainControl.setValue(min);
//                        
//                    } catch (LineUnavailableException ex) {
//                        Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
    }

    public void enableSound() {
//        clipMap.values().stream()
//                .forEach(clip -> {
//                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//                    gainControl.setValue(+6.0f); // Reduce volume by 10 decibels.
//
//                });
    }
}
