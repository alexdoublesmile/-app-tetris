package com.joyful.tetris.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ColorHelper {
    private static final List<Color> AVAILABLE_COLORS = Arrays.asList(
            new Color(0, 100, 200), 
            new Color(50, 180, 100), 
            new Color(50, 200, 200), 
            new Color(255, 255, 150), 
            new Color(255, 155, 50), 
            new Color(200, 100, 100)
    );

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
