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

    public static String getPercentByDouble(double num) {
        double numPercent = num * 100;
        StringBuilder percentBuilder = new StringBuilder();

        String percentString = String.valueOf(numPercent);
        String symbols = percentString.length() > 5
                ? percentString.substring(0, 5)
                : percentString;
        return percentBuilder.append(symbols).append("%").toString();
    }
}
