package com.joyful.tetris.util;

import com.joyful.tetris.model.PlayerRank;

import java.awt.*;
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

    public static Color getNewColor(PlayerRank rank) {
        List<Color> availableColors = rank.getColors();

        return availableColors.get(ThreadLocalRandom.current().nextInt(availableColors.size()));
    }

    public static Color getDarker(Color color, int times) {
        int red = color.getRed() - 5 * times;
        int green = color.getGreen() - 5 * times;
        int blue = color.getBlue() - 5 * times;
        return new Color(red < 0 ? 0 : red, green < 0 ? 0 : green, blue < 0 ? 0 : blue);
    }
}
