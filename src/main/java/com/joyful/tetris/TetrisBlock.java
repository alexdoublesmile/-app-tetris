package com.joyful.tetris;

import java.awt.Color;

public class TetrisBlock {
    private int x;
    private int y;
    private int[][] shape;
    private Color color;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        x = 4;
        y = -3;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }
    
    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }
}
