package com.joyful.tetris;

import java.awt.Color;
import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import java.util.Random;

public class TetrisBlock {
    private int x;
    private int y;
    private int[][] shape;
    private int[][][] shapes;
    private int currentRotation;
    private Color color;
    private Color[] availableColors = {GREEN, RED, BLUE};
    private int shift;

    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        
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
    
    public void spawn(int gridWidth, Color previousColor) {
        Random random = new Random();
        currentRotation = random.nextInt(shapes.length);
        shape = shapes[currentRotation];
        
        y = -getHeight();
        x = random.nextInt(gridWidth - getWidth());
        do {
            color = availableColors[random.nextInt(availableColors.length)];
        } while (color.equals(previousColor));
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    void unrotate() {
        rotate();
        rotate();
        rotate();
    }

    void saveShift(int newX) {
        shift = x - newX;
    }

    void unshift() {
        x += shift;
        shift = 0;
    }

    public int[][][] getShapes() {
        return shapes;
    }

    public int getCurrentRotation() {
        return currentRotation;
    }
}
