package com.joyful.tetris.audio;

import com.joyful.tetris.util.AudioHelper;
import java.util.Arrays;
import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import javax.sound.sampled.Clip;

public enum AudioType {
    GAME_OVER("gameOver.wav"),
    CLEAR_LINE("clearLine.wav"),
    CLEAR_FOUR_LINES("clearFourLines.wav");

    public static Map<AudioType, Clip> mapAllTypesToClip() {
        return Arrays.stream(values()).collect(toMap(
                        identity(), 
                        type -> AudioHelper.getClip(type.getFileName())));
    }
    
    private final String fileName;

    private AudioType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
