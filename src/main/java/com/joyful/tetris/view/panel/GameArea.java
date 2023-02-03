package com.joyful.tetris.view.panel;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.TetrisBlock;
import com.joyful.tetris.util.BlockHelper;
import com.joyful.tetris.util.ColorHelper;
import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        drawBackgrouond(g2);
        drawBlock(g2);
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

    private void drawBlock(Graphics2D g) {
        if (nonNull(currentBlock)) {
            int[][] shape = currentBlock.getShape();
            for (int row = 0; row < currentBlock.getHeight(); row++) {
                for (int col = 0; col < currentBlock.getWidth(); col++) {
                    if (shape[row][col] == 1) {
                        int x = (currentBlock.getX() + col) * cellSize - 1;
                        int y = (currentBlock.getY() + row) * cellSize - 1;

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

    private void drawBackgrouond(Graphics2D g) {
        for (int i = gridRows - 1; i > 0; i--) {
            for (int j = 0; j < gridColumns; j++) {
                Color color = background[i][j];
                if (color != null) {
                    drawGridSquare(g, color, j * cellSize - 1, i * cellSize - 1);
                }
            }
        }
    }   

    private void drawGridSquare(Graphics2D g, Color color, int x, int y) {
        if (nonNull(color)) {
            g.setColor(color);
            g.fillRect(x, y, cellSize, cellSize);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 10));
            g.drawRect(x, y, cellSize, cellSize);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 9));
            g.drawRect(x + 1, y + 1, cellSize - 2, cellSize - 2);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 8));
            g.drawRect(x + 2, y + 2, cellSize - 4, cellSize - 4);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 7));
            g.drawRect(x + 3, y + 3, cellSize - 6, cellSize - 6);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 6));
            g.drawRect(x + 4, y + 4, cellSize - 8, cellSize - 8);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 5));
            g.drawRect(x + 5, y + 5, cellSize - 10, cellSize - 10);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 4));
            g.drawRect(x + 6, y + 6, cellSize - 12, cellSize - 12);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 3));
            g.drawRect(x + 7, y + 7, cellSize - 14, cellSize - 14);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 2));
            g.drawRect(x + 8, y + 8, cellSize - 16, cellSize - 16);

            g.setStroke(new BasicStroke(1));
            g.setColor(ColorHelper.getDarker(color, 1));
            g.drawRect(x + 9, y + 9, cellSize - 18, cellSize - 18);
        }
    }

    public void setMiniPanel(MiniPanel miniPanel) {
        this.miniPanel = miniPanel;
    }

    public void initBackground() {
        background = new Color[gridRows][gridColumns];
    }
}
