package com.joyful.tetris.audio;

import com.joyful.tetris.util.AudioHelper;
import java.util.Arrays;
import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public enum AudioType {
    START_MENU("start_menu.wav"),
    SELECT("select.wav"),
    START_GAME("start_game.wav"),
    FAST_DROP("fast_drop.wav"),
    PAUSE("pause.wav"),
    CLEAR_LINE("clear_line.wav"),
    CLEAR_FOUR_LINES("clear_four_lines.wav"),
    BEGINNER("beginner.wav"),
    EXPERIENCED("experienced.wav"),
    EXPERT("expert.wav"),
    MASTER("master.wav"),
    SENIOR("senior.wav"),
    GAME_OVER("game_over.wav");

    public static Map<AudioType, Clip> mapAllTypesToClip() {
        return Arrays.stream(values()).collect(toMap(
                        identity(), 
                        type -> AudioHelper.getClip(type.getFileName())));
    }

    public static Map<AudioType, AudioInputStream> mapAllTypesToStream() {
        return Arrays.stream(values()).collect(toMap(
                identity(),
                type -> AudioHelper.getStream(type.getFileName())));
    }
    
    private final String fileName;

    private AudioType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
