package com.joyful.tetris.view.panel;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.TetrisBlock;
import com.joyful.tetris.util.BlockHelper;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Graphics;
import static java.util.Objects.nonNull;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private static final int gridColumns = 10;
    private final int cellSize;
    private final int gridRows;
    
    private MiniPanel miniPanel;
    private Color[][] background;
    
    private TetrisBlock currentBlock;
    private TetrisBlock nextBlock;
    
    public GameArea(JPanel placeholder) {
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        cellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / cellSize;
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
        for (int row = gridRows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < gridColumns; col++) {
                if (background[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }
            if (lineFilled) {
                clearLine(row);
                linesCleared++;
                
                shiftDown(row);
                
                clearLine(0);
                
                row++;
                repaint();
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
        if (currentBlock.getY() < 0) {
            currentBlock = null;
            return true;
        }
        return false;
    }

    public TetrisBlock getNextBlock() {
        return nextBlock;
    }
    
    public void moveBlockToBackground() {
        for (int i = 0; i < currentBlock.getHeight(); i++) {
            for (int j = 0; j < currentBlock.getWidth(); j++) {
                if (currentBlock.getShape()[i][j] == 1) {
                    background[i + currentBlock.getY()][j + currentBlock.getX()] = currentBlock.getColor();
                }
            }
        }
    }
    
    public void moveBlockRight() {
        if (currentBlock == null) {
            return;
        }
        if (checkRight()) {
            currentBlock.moveRight();
            repaint();
        }
    }

    public void moveBlockLeft() {
        if (currentBlock == null) {
            return;
        }
        if (checkLeft()) {
            currentBlock.moveLeft();
            repaint();
        }
    }

    public void dropBlock() {
        if (currentBlock == null) {
            return;
        }
        while(checkBottom()) {
            currentBlock.moveDown();
            repaint();
        }
        Launcher.playFastDrop();
    }

    public void rotateBlock() {
        if (currentBlock == null) {
            return;
        }
        currentBlock.rotate();
        
        // prevent sides or bottom rotation
        if (currentBlock.getLeftEdge() < 0) {
            currentBlock.saveShift(0);
            currentBlock.setX(0);
        }
        if (currentBlock.getBottomCoord() >= gridRows) {
            currentBlock.setY(gridRows - currentBlock.getHeight());
        }
        if (currentBlock.getRightEdge() >= gridColumns) {
            int newX = gridColumns - currentBlock.getWidth();
            currentBlock.saveShift(newX);
            currentBlock.setX(newX);
        }
        
        // prevent background rotation
        for (int row = 0; row < currentBlock.getHeight(); row++) {
            for (int col = 0; col < currentBlock.getWidth(); col++) {
                int gridRow = (currentBlock.getY() + row);
                int gridColumn = (currentBlock.getX() + col);
                
                // break if under screen
                if (gridRow < 0) {
                    break;
                }
                if (currentBlock.getShape()[row][col] != 0 
                        && background[gridRow][gridColumn] != null) {
                        currentBlock.unrotate();
                        currentBlock.unshift();
                        return;
                }
            }
        }
        repaint();
    }

    public void spawnBlock() {
        Color previousColor = nonNull(currentBlock) 
                ? currentBlock.getColor() 
                : WHITE;
        
        if (nextBlock == null) {
            currentBlock = BlockHelper.getNewBlock(currentBlock);
            currentBlock.spawn(gridColumns, previousColor);
        } else {
            currentBlock = nextBlock;
        }
        nextBlock = BlockHelper.getNewBlock(currentBlock);
        nextBlock.spawn(gridColumns, previousColor);

        miniPanel.setNextBlock(nextBlock);
        miniPanel.repaint();
    }

    private void drawBlock(Graphics g) {
        if (nonNull(currentBlock)) {
            int[][] shape = currentBlock.getShape();
            for (int row = 0; row < currentBlock.getHeight(); row++) {
                for (int col = 0; col < currentBlock.getWidth(); col++) {
                    if (shape[row][col] == 1) {
                        int x = (currentBlock.getX() + col) * cellSize;
                        int y = (currentBlock.getY() + row) * cellSize;

                        drawGridSquare(g, currentBlock.getColor(), x, y);
                    }
                }            
            }
        }
    }
    
    public boolean moveBlockDown() {
        if (!checkBottom()) {
            return false;
        } else {
            currentBlock.moveDown();
            repaint();
            return true;
        }
    }

    private boolean checkBottom() {
        if (currentBlock.getBottomCoord() == gridRows) {
            return false;
        } 
        int[][] shape = currentBlock.getShape();
        int width = currentBlock.getWidth();
        int height = currentBlock.getHeight();
        for (int col = 0; col < width; col++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + currentBlock.getX();
                    int y = row + currentBlock.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkLeft() {
        if (currentBlock.getLeftEdge() == 0) {
            return false;
        } 
        int[][] shape = currentBlock.getShape();
        int width = currentBlock.getWidth();
        int height = currentBlock.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = col + currentBlock.getX() - 1;
                    int y = row + currentBlock.getY();
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
        if (currentBlock.getRightEdge() == gridColumns) {
            return false;
        }
        int[][] shape = currentBlock.getShape();
        int width = currentBlock.getWidth();
        int height = currentBlock.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + currentBlock.getX() + 1;
                    int y = row + currentBlock.getY();
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

    public void setMiniPanel(MiniPanel miniPanel) {
        this.miniPanel = miniPanel;
    }

    public void initBackground() {
        background = new Color[gridRows][gridColumns];
    }
    
}
