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
    
    public void singlePlay(AudioType soundType) {
        Clip soundClip = clipMap.get(soundType);
        soundClip.setFramePosition(0);
        soundClip.start();
    }

    public void loopPlay(AudioType soundType) {
        AudioInputStream stream = streamMap.get(soundType);
        AudioFormat format = stream.getFormat();
        
        Line line;
        try {
            Clip clip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            line = AudioSystem.getLine(info);
            
            if (!line.isOpen()) {
                clip.open(stream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
