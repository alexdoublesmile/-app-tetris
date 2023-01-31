package com.joyful.tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameArea extends JPanel { 
    public GameArea() {
        setBounds(0, 0, 100, 100);
        setBackground(Color.RED);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, 50, 50);
    }
}
