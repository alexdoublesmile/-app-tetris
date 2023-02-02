package com.joyful.tetris.util;

import java.awt.Color;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class ColorHelper {
    private static final List<Color> AVAILABLE_COLORS = Arrays.asList(GREEN, RED, BLUE);
    private static final Random RANDOM = new Random();


    public static Color getNextColor(Color previousColor) {
        Color color;
        do {
            color = AVAILABLE_COLORS.get(RANDOM.nextInt(AVAILABLE_COLORS.size()));
        } while (color.equals(previousColor));
        return color;
    }
}
