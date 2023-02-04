package com.joyful.tetris.model;

import com.joyful.tetris.util.BlockHelper;
import com.joyful.tetris.util.ColorHelper;
import java.awt.Color;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class TetrisBlock {
    private int x;
    private int y;
    private int[][] shape;
    private int[][][] shapeRotations;
    private int rotation;
    private Color color;
    private int shift;

    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        shapeRotations = BlockHelper.getShapeRotations(shape);
    }
    
    public void spawn(int gridWidth, PlayerRank rank) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        rotation = random.nextInt(shapeRotations.length);
        shape = shapeRotations[rotation];
        
        y = -getHeight();
        x = random.nextInt(gridWidth - getWidth());
        color = ColorHelper.getNewColor(rank);
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
        rotation = rotation == 3 ? 0 : ++rotation;
        shape = shapeRotations[rotation];
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
        return rotation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Arrays.deepHashCode(this.shape);
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
        return Arrays.deepEquals(this.shape, other.shape);
    }
}
