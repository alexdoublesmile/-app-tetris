package com.joyful.tetris.model.block;

import com.joyful.tetris.model.TetrisBlock;

public class IShape extends TetrisBlock {

    public IShape() {
        super(new int[][] {{1, 1, 1, 1}});
    }

    @Override
    public void rotate() {
        super.rotate();
        if (getWidth() == 1) {
            setX(getX() + 1);
            setY(getY() - 1);
        } else {
            setX(getX() - 1);
            setY(getY() + 1);
        }
    }
}
