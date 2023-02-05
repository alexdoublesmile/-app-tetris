package com.joyful.tetris.view;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.PlayerRank;
import com.joyful.tetris.service.GameThread;
import com.joyful.tetris.util.ScoreHelper;
import com.joyful.tetris.view.panel.GameArea;
import com.joyful.tetris.view.panel.MiniPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public final class GameForm extends JFrame {
    private final GameArea gameArea;
    private final MiniPanel miniPanel;
    private GameThread gameThread;
    
    private boolean paused;
    
    public GameForm(String title) {
        super(title);
        initComponents();
        setIconImage(new ImageIcon("icon.png").getImage());
        
        gameArea = new GameArea(gameAreaPlaceholder);
        miniPanel = new MiniPanel(miniPanelPlaceholder);
        gameArea.setMiniPanel(miniPanel);
        
        add(gameArea);
        add(miniPanel);
        
        initControls();
        initSoundControl();
    }
    
    public void startGame() {
        
        gameArea.initBackground();
        
        gameThread = new GameThread(gameArea, this);
        gameThread.start();
    }

    private void initControls() {
        InputMap im = getRootPane().getInputMap();
        ActionMap am = getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("SPACE"), "pause");
        
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockRight(paused);
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft(paused);
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.dropBlock(paused);
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlock(paused);
            }
        });
        am.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        scoreDisplay = new javax.swing.JLabel();
        levelDisplay = new javax.swing.JLabel();
        miniPanelPlaceholder = new javax.swing.JPanel();
        btnMainMenu = new javax.swing.JButton();
        linesDisplay = new javax.swing.JLabel();
        speedDisplay = new javax.swing.JLabel();
        rankDisplay = new javax.swing.JLabel();
        efficiencyDisplay = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        btnMusic = new javax.swing.JToggleButton();
        pauseMessage = new javax.swing.JLabel();
        btnSound = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameAreaPlaceholder.setPreferredSize(new java.awt.Dimension(200, 400));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        scoreDisplay.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        scoreDisplay.setText("Score: 0");

        levelDisplay.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        levelDisplay.setText("Level: 1");

        miniPanelPlaceholder.setPreferredSize(new java.awt.Dimension(82, 82));

        javax.swing.GroupLayout miniPanelPlaceholderLayout = new javax.swing.GroupLayout(miniPanelPlaceholder);
        miniPanelPlaceholder.setLayout(miniPanelPlaceholderLayout);
        miniPanelPlaceholderLayout.setHorizontalGroup(
            miniPanelPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        miniPanelPlaceholderLayout.setVerticalGroup(
            miniPanelPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        btnMainMenu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusPainted(false);
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        linesDisplay.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        linesDisplay.setText("Lines: 0");

        speedDisplay.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        speedDisplay.setText("Speed: 0");

        rankDisplay.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rankDisplay.setText("Noob");

        efficiencyDisplay.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        efficiencyDisplay.setText("Eff: 0");

        btnPause.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnPause.setText("Pause");
        btnPause.setFocusPainted(false);
        btnPause.setFocusable(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnMusic.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnMusic.setSelected(true);
        btnMusic.setText("Music");
        btnMusic.setFocusPainted(false);
        btnMusic.setFocusable(false);
        btnMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMusicActionPerformed(evt);
            }
        });

        pauseMessage.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        pauseMessage.setForeground(new java.awt.Color(102, 102, 102));
        pauseMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pauseMessage.setLabelFor(this);
        pauseMessage.setFocusable(false);
        pauseMessage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pauseMessage.setRequestFocusEnabled(false);

        btnSound.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        btnSound.setSelected(true);
        btnSound.setText("Sound");
        btnSound.setFocusPainted(false);
        btnSound.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(efficiencyDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(speedDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(miniPanelPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scoreDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rankDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(linesDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(levelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pauseMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMusic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSound)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(rankDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scoreDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(linesDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(levelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(pauseMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMainMenu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPause)
                                .addGap(22, 22, 22)
                                .addComponent(speedDisplay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(efficiencyDisplay)
                                .addGap(56, 56, 56)
                                .addComponent(miniPanelPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMusic)
                            .addComponent(btnSound))
                        .addGap(15, 15, 15))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        resetPause();
        setVisible(false);
        gameThread.interrupt();

        Launcher.playSelect();

        Launcher.showStartup();

    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        togglePause();
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMusicActionPerformed

    }//GEN-LAST:event_btnMusicActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GameForm("Tetris Pro").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JToggleButton btnMusic;
    private javax.swing.JButton btnPause;
    private javax.swing.JToggleButton btnSound;
    private javax.swing.JLabel efficiencyDisplay;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel levelDisplay;
    private javax.swing.JLabel linesDisplay;
    private javax.swing.JPanel miniPanelPlaceholder;
    private javax.swing.JLabel pauseMessage;
    private javax.swing.JLabel rankDisplay;
    private javax.swing.JLabel scoreDisplay;
    private javax.swing.JLabel speedDisplay;
    // End of variables declaration//GEN-END:variables

    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }

    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }
    
    public void updateRank(PlayerRank rank) {
        rankDisplay.setText(rank.getTitle());
    }

    public void updateLines(int lines) {
        linesDisplay.setText("Lines: " + lines);
    }

    public void updateSpeed(double speed) {
        String speedPercent = ScoreHelper.getPercentByDouble(speed);
        
        speedDisplay.setText("Speed: " + speedPercent);
    }
        
    public void updateEfficiency(double efficiency) {
        String efficiencyPercent = ScoreHelper.getPercentByDouble(efficiency);
        
        efficiencyDisplay.setText("Eff: " + efficiencyPercent);
    }

    private void initSoundControl() {
        btnMusic.addItemListener(e -> {
            if (ItemEvent.DESELECTED == e.getStateChange()) {
                Launcher.disableMusic();
            } else {
                Launcher.enableMusic();
            }
        });
        btnSound.addItemListener(e -> {
            if (ItemEvent.DESELECTED == e.getStateChange()) {
                Launcher.disableSounds();
            } else {
                Launcher.enableSounds();
            }
        });
    }

    private void togglePause() {
        paused = gameThread.togglePause();
        pauseMessage.setText(paused ? "P  A  U  S  E" : "");
    }

    private void resetPause() {
        paused = false;
        pauseMessage.setText("");
    }
}
