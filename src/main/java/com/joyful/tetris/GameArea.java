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
        
        gridColumns = 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 50, 50);
    }
}
