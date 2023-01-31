package com.joyful.tetris;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private final int cellSize;
    private final int rowsNumber;
    private final int columnsNumber;
    
    public GameArea(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        columnsNumber = 10;
        cellSize = getBounds().width / columnsNumber;
        rowsNumber = getBounds().height / cellSize;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int j = 0; j < rowsNumber; j++) {
            for (int i = 0; i < columnsNumber; i++) {
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);            
            }
        }
    }
}
