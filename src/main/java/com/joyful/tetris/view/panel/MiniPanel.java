package com.joyful.tetris.view.panel;

import com.joyful.tetris.model.TetrisBlock;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static java.util.Objects.nonNull;
import javax.swing.JPanel;

public class MiniPanel extends JPanel {
    private final int cellSize = 15;
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (nonNull(nextBlock)) {
            int[][] nextShape = nextBlock.getShape();
            for (int i = 0; i < nextShape.length; i++) {
                for (int j = 0; j < nextShape[0].length; j++) {
                    if (nextShape[i][j] != 0) {
                        Color color = nextBlock.getColor();
                        int x = j * cellSize;
                        int y = i * cellSize;
                        
                        g2.setColor(color);
                        g2.fillRect(x, y, cellSize, cellSize);

                        g2.setColor(BLACK);
                        g2.drawRect(x, y, cellSize, cellSize);

//                        g2.setColor(ColorHelper.getDarker(color, 10));
//                        g2.drawRect(x, y, cellSize, cellSize);
//
//                        g2.setColor(ColorHelper.getDarker(color, 9));
//                        g2.drawRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
//
//                        g2.setColor(ColorHelper.getDarker(color, 8));
//                        g2.drawRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
//
//                        g2.setColor(ColorHelper.getDarker(color, 7));
//                        g2.drawRect(x + 3, y + 3, cellSize - 6, cellSize - 6);
                    }
                }
            }
        }
    }

    void setNextBlock(TetrisBlock nextBlock) {
        this.nextBlock = nextBlock;
    }
}
