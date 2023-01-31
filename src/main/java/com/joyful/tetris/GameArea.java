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
    
    public void clearLines() {
        boolean lineFilled;
        for (int r = gridRows - 1; r >= 0; r--) {
            lineFilled = true;
            for (int c = 0; c < gridColumns; c++) {
                if (background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;
                repaint();
            }
        }
    }
    
    public void clearLine(int row) {
        for (int i = 0; i < gridColumns; i++) {
            background[row][i] = null;
        }
    }

    public void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row - 1][col];
            }
        }
    }
    
    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            return true;
        }
        return false;
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
        if (checkRight()) {
            block.moveRight();
            repaint();
        }
    }

    public void moveBlockLeft() {
        if (checkLeft()) {
            block.moveLeft();
            repaint();
        }
    }

    public void dropBlock() {
        while(checkBottom()) {
            block.moveDown();
            repaint();
        }
    }

    public void rotateBlock() {
        block.rotate();
        repaint();
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
        if (!checkBottom()) {
            moveBlockToBackground();
            clearLines();
            return false;
        } else {
            block.moveDown();
            repaint();
            return true;
        }
    }

    private boolean checkBottom() {
        if (block.getBottomCoord() == gridRows) {
            return false;
        } 
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();
        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        } 
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkRight() {
        if (block.getRightEdge() == gridColumns) {
            return false;
        }
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
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
