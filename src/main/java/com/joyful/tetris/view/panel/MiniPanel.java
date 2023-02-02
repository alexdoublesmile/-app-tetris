package com.joyful.tetris.view.panel;

import com.joyful.tetris.model.TetrisBlock;
import static java.awt.Color.BLACK;
import java.awt.Graphics;
import static java.util.Objects.nonNull;
import javax.swing.JPanel;

public class MiniPanel extends JPanel {
    private final int miniPanelCellSize = 15;
    private final int miniPanelRows = 4;
    private final int miniPanelColumns = 4;
    
    private TetrisBlock nextBlock;
    
    public MiniPanel(JPanel placeholder) {
        placeholder.setVisible(false);
        setBounds(placeholder.getBounds());
        setBackground(placeholder.getBackground());
        setBorder(placeholder.getBorder());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (nonNull(nextBlock)) {
            int[][] nextShape = nextBlock.getShape();
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

    void setNextBlock(TetrisBlock nextBlock) {
        this.nextBlock = nextBlock;
    }
}
