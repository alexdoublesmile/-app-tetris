package com.joyful.tetris.util;

import com.joyful.tetris.model.TetrisBlock;
import com.joyful.tetris.model.block.IShape;
import com.joyful.tetris.model.block.JShape;
import com.joyful.tetris.model.block.LShape;
import com.joyful.tetris.model.block.OShape;
import com.joyful.tetris.model.block.SShape;
import com.joyful.tetris.model.block.TShape;
import com.joyful.tetris.model.block.ZShape;
import java.util.concurrent.ThreadLocalRandom;

public final class BlockHelper {
    private static final TetrisBlock[] allBlocks = new TetrisBlock[]{
        new IShape(),
        new JShape(),
        new LShape(),
        new OShape(),
        new SShape(),
        new TShape(),
        new ZShape()
    };

    public static TetrisBlock getNewBlock(TetrisBlock currentBlock) {
        TetrisBlock newBlock;
        do {
            newBlock = allBlocks[ThreadLocalRandom.current().nextInt(allBlocks.length)];
        } while (newBlock.equals(currentBlock));
        return newBlock;
    }

    public static int[][][] getShapeRotations(int[][] shape) {
        int[][][] shapeRotations = new int[4][][];
        for (int i = 0; i < shapeRotations.length; i++) {
            int rows = shape[0].length;
            int cols = shape.length;
            shapeRotations[i] = new int[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    shapeRotations[i][y][x] = shape[cols - x - 1][y];
                }
            }
            shape = shapeRotations[i];
        }
        return shapeRotations;
    }
}
