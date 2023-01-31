package com.joyful.tetris;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private final int cellSize;
    private final int rowsNumber;
    private final int columnsNumber;
    
    private TetrisBlock block;
    
    public GameArea(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        columnsNumber = 10;
        cellSize = getBounds().width / columnsNumber;
        rowsNumber = getBounds().height / cellSize;
        
        spawnBlock();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (int j = 0; j < rowsNumber; j++) {
//            for (int i = 0; i < columnsNumber; i++) {
//                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);            
//            }
//        }
        drawBlock(g);
    }

    private void drawBlock(Graphics g) {
        int[][] shape = block.getShape();
        
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (shape[row][col] == 1) {
                    int x = (block.getX() + col) * cellSize;
                    int y = (block.getY() + row) * cellSize;
                    g.setColor(block.getColor());
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(BLACK);
                    g.drawRect(x, y, cellSize, cellSize);
                }
            }            
        }
    }

    private void spawnBlock() {
        block = new TetrisBlock(new int[][]{{1, 0}, {1, 0}, {1, 1}}, BLUE);
    }
    
    public void moveBlockDown() {
        block.moveDown();
        repaint();
    }
}
