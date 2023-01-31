package com.joyful.tetris;

import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private final int cellSize;
    private final int gridRows;
    private final int gridColumns;
    private Color[][] background;
    
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        gridColumns = 10;
        cellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / cellSize;
        
        background = new Color[gridRows][gridColumns];
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawBackgrouond(g);
        drawBlock(g);
    }
    
    private void moveBlockToBackground() {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getShape()[i][j] == 1) {
                    background[i + block.getY()][j + block.getX()] = block.getColor();
                }
            }
        }
    }
    
    public void moveBlockRight() {
        block.moveRight();
        repaint();
    }

    public void moveBlockLeft() {
        block.moveLeft();
        repaint();
    }

    public void dropBlock() {
        while(!isBottom()) {
            block.moveDown();
        }
        repaint();
    }

    public void rotateBlock() {

    }

    private void drawBlock(Graphics g) {
        int[][] shape = block.getShape();
        
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (shape[row][col] == 1) {
                    int x = (block.getX() + col) * cellSize;
                    int y = (block.getY() + row) * cellSize;

                    drawGridSquare(g, block.getColor(), x, y);
                }
            }            
        }
    }

    public void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, BLUE);
        block.spawn(gridColumns);
    }
    
    public boolean moveBlockDown() {
        if (isBottom()) {
            moveBlockToBackground();
            return false;
        } else {
            block.moveDown();
            repaint();
            return true;
        }
    }

    private boolean isBottom() {
        return block.getBottomCoord() == gridRows; 
    }

    private void drawBackgrouond(Graphics g) {
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                Color color = background[i][j];
                if (color != null) {
                    drawGridSquare(g, color, j * cellSize, i * cellSize);
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
        g.setColor(BLACK);
        g.drawRect(x, y, cellSize, cellSize);
    }
}
