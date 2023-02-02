package com.joyful.tetris.util;

public final class ScoreHelper {
    public static int getScore(int lines) {
        switch (lines) {
                case 1: return 100;
                case 2: return 220;
                case 3: return 350;
                case 4: return 500;
                default: return 0;
        }
    }
}
