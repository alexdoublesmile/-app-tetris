package com.joyful.tetris.util;

import java.awt.Color;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ColorHelper {
    private static final List<Color> AVAILABLE_COLORS = Arrays.asList(GREEN, RED, BLUE);

    public static Color getNewColor(Color previousColor) {
        Color color;
        do {
            color = AVAILABLE_COLORS.get(ThreadLocalRandom.current().nextInt(AVAILABLE_COLORS.size()));
        } while (color.equals(previousColor));
        return color;
    }

    public static Color getNewColor() {
        return AVAILABLE_COLORS.get(ThreadLocalRandom.current().nextInt(AVAILABLE_COLORS.size()));
    }
}
