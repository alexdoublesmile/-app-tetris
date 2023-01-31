package com.joyful.tetris;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    private final int gridCellSize;
    private final int gridRows;
    private final int gridColumns;
    
    public GameArea(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        gridColumns = 10;
        gridCellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / gridCellSize;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int j = 0; j < gridRows; j++) {
            for (int i = 0; i < gridColumns; i++) {
                g.drawRect(i * gridCellSize, j * gridCellSize, gridCellSize, gridCellSize);            
            }
        }
    }
}
