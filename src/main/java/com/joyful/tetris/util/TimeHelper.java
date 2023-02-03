package com.joyful.tetris.util;

public final class TimeHelper {

    public static double getSeconds(long nanos) {
        return nanos / 1000_000_000;
    }

}
