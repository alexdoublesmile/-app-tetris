package com.joyful.tetris.audio;

import java.util.Map;
import javax.sound.sampled.Clip;

public class AudioPlayer {    
    private static final Map<AudioType, Clip> allSounds = AudioType.mapAllTypesToClip();
    
    public void play(AudioType soundType) {
        Clip soundClip = allSounds.get(soundType);
        soundClip.setFramePosition(0);
        soundClip.start();
    }
}
