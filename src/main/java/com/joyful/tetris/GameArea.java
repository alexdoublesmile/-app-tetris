package com.joyful.tetris;

import com.joyful.tetris.block.*;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private JPanel miniPanel;
    
    private final int cellSize;
    private final int gridRows;
    private final int gridColumns;
    private Color[][] background;
    
    private TetrisBlock nextBlock;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    
    public GameArea(JPanel placeholder) {
//        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        gridColumns = 10;
        cellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / cellSize;
        
        blocks = new TetrisBlock[]{
            new IShape(),
            new JShape(),
            new LShape(),
            new OShape(),
            new SShape(),
            new TShape(),
            new ZShape()
        };
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawBackgrouond(g);
        drawBlock(g);
    }
    
    public int clearLines() {
        boolean lineFilled;
        int linesCleared = 0;
        for (int r = gridRows - 1; r >= 0; r--) {
            lineFilled = true;
            for (int c = 0; c < gridColumns; c++) {
                if (background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                linesCleared++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;
                repaint();
                
                if (linesCleared > 0) {
                    Launcher.playClearLine();
                }
            }
        }
        return linesCleared;
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
//            block = null;
            return true;
        }
        return false;
    }

    public TetrisBlock getNextBlock() {
        return nextBlock;
    }
    
    public void moveBlockToBackground() {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getShape()[i][j] == 1) {
                    background[i + block.getY()][j + block.getX()] = block.getColor();
                }
            }
        }
    }
    
    public void moveBlockRight() {
        if (block == null) {
            return;
        }
        if (checkRight()) {
            block.moveRight();
            repaint();
        }
    }

    public void moveBlockLeft() {
        if (block == null) {
            return;
        }
        if (checkLeft()) {
            block.moveLeft();
            repaint();
        }
    }

    public void dropBlock() {
        if (block == null) {
            return;
        }
        while(checkBottom()) {
            block.moveDown();
            repaint();
        }
    }

    public void rotateBlock() {
        if (block == null) {
            return;
        }
        block.rotate();
        
        // prevent sides or bottom rotation
        if (block.getLeftEdge() < 0) {
            block.saveShift(0);
            block.setX(0);
        }
        if (block.getBottomCoord() >= gridRows) {
            block.setY(gridRows - block.getHeight());
        }
        if (block.getRightEdge() >= gridColumns) {
            int newX = gridColumns - block.getWidth();
            block.saveShift(newX);
            block.setX(newX);
        }
        
        // prevent background rotation
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                int gridRow = (block.getY() + row);
                int gridColumn = (block.getX() + col);
                
                // break if under screen
                if (gridRow < 0) {
                    break;
                }
                if (block.getShape()[row][col] != 0 
                        && background[gridRow][gridColumn] != null) {
                        block.unrotate();
                        block.unshift();
                        return;
                }
            }
        }

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
        Random random = new Random();
        Color previousColor = null;
        if (block == null) {
            block = blocks[random.nextInt(blocks.length)];
            block.spawn(gridColumns, previousColor);
        } else {
            block = nextBlock;
        }        
        do {
            nextBlock = blocks[random.nextInt(blocks.length)];
        } while (Arrays.deepEquals(nextBlock.getShape(), block.getShape()));
        nextBlock.spawn(gridColumns, block.getColor());
        
        miniPanel.repaint();
    }
    
    public boolean moveBlockDown() {
        if (!checkBottom()) {
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

    public void setMiniPanel(JPanel miniPanel) {
        this.miniPanel = miniPanel;
    }

    void initBackground() {
        background = new Color[gridRows][gridColumns];
    }
    
}
