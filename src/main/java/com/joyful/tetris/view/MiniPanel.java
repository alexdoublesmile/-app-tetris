package com.joyful.tetris.view;

import com.joyful.tetris.model.TetrisBlock;
import com.joyful.tetris.view.GameArea;
import static java.awt.Color.BLACK;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MiniPanel extends JPanel {
    private final int miniPanelCellSize = 20;
    private final int miniPanelRows = 4;
    private final int miniPanelColumns = 4;
    
    private final GameArea gameArea;
    
    public MiniPanel(GameArea gameArea, JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
        
        this.gameArea = gameArea;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        TetrisBlock nextBlock = gameArea.getNextBlock();
        int[][] nextShape = nextBlock.getShapes()[nextBlock.getCurrentRotation()];
        for (int i = 0; i < nextShape.length; i++) {
            for (int j = 0; j < nextShape[0].length; j++) {
                if (nextShape[i][j] != 0) {
                    g.setColor(nextBlock.getColor());
                    g.fillRect(j * miniPanelCellSize, i * miniPanelCellSize, miniPanelCellSize, miniPanelCellSize);
                    g.setColor(BLACK);
                    g.drawRect(j * miniPanelCellSize, i * miniPanelCellSize, miniPanelCellSize, miniPanelCellSize);
                }
            }
        }
    }
}
