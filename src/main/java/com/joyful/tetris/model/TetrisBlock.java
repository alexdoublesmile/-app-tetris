package com.joyful.tetris.model;

import com.joyful.tetris.util.ColorHelper;
import java.awt.Color;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TetrisBlock {
    private int x;
    private int y;
    private int[][] currentShape;
    private int currentRotation;
    private int[][][] shapeRotations;
    private Color color;
    private int shift;

    public TetrisBlock(int[][] shape) {
        this.currentShape = shape;
        
        initShapes();
    }
    
    private void initShapes() {
        shapeRotations = new int[4][][];
        for (int i = 0; i < shapeRotations.length; i++) {
            int rows = currentShape[0].length;
            int cols = currentShape.length;
            shapeRotations[i] = new int[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    shapeRotations[i][y][x] = currentShape[cols - x - 1][y];
                }
            }
            currentShape = shapeRotations[i];
        }
    }
    
    public void spawn(int gridWidth, Color previousColor) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        currentRotation = random.nextInt(shapeRotations.length);
        currentShape = shapeRotations[currentRotation];
        
        y = -getHeight();
        x = random.nextInt(gridWidth - getWidth());
        color = ColorHelper.getNewColor(previousColor);
    }

    public int[][] getShape() {
        return currentShape;
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
        return currentShape.length;
    }

    public int getWidth() {
        return currentShape[0].length;
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
        currentShape = shapeRotations[currentRotation];
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

    public void unrotate() {
        rotate();
        rotate();
        rotate();
    }

    public void saveShift(int newX) {
        shift = x - newX;
    }

    public void unshift() {
        x += shift;
        shift = 0;
    }

    public int[][][] getShapes() {
        return shapeRotations;
    }

    public int getCurrentRotation() {
        return currentRotation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Arrays.deepHashCode(this.currentShape);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TetrisBlock other = (TetrisBlock) obj;
        return Arrays.deepEquals(this.currentShape, other.currentShape);
    }
}
