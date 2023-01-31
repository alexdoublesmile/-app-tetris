package com.joyful.tetris;

import java.awt.Color;

public class TetrisBlock {
    private int x;
    private int y;
    private int[][] shape;
    private int[][][] shapes;
    private int currentRotation;
    private Color color;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        
        initShapes();
    }
    
    private void initShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < shapes.length; i++) {
            int rows = shape[0].length;
            int cols = shape.length;
            shapes[i] = new int[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    shapes[i][y][x] = shape[cols - x - 1][y];
                }
            }
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[0];
        
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;
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
    
    public void rotate() {
        currentRotation = currentRotation == 3 ? 0 : ++currentRotation;
        shape = shapes[currentRotation];
    }
    
    public int getBottomCoord() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x;
    }

    public int getRightEdge() {
        return x + getWidth();
    }
}
