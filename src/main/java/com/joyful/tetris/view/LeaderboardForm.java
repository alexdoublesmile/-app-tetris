package com.joyful.tetris.view;

import com.joyful.tetris.Launcher;
import com.joyful.tetris.model.PlayerInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class LeaderboardForm extends javax.swing.JFrame {
    private static final String leaderboardFilename = "leaderboard";

    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    
    public LeaderboardForm(String title) {
        super(title);
        initComponents();
        setIconImage(new ImageIcon("icon.png").getImage());
        model = (DefaultTableModel) leaderboard.getModel();

        initTableData();
        initTableSorter();
    }   
    
    public void addPlayer(PlayerInfo info) {
        String playerName = info.getPlayerName();
        int score = info.getScore();
        int lines = info.getLines();
        String rank = info.getRank();
        int level = info.getLevel();
        String speed = info.getSpeed();
        String efficiency = info.getEfficiency();
        model.addRow(new Object[]{playerName, score, lines, rank, level, speed, efficiency});
        sorter.sort();

        try {
            saveLeaderboard();
        } catch (IOException ex) {
            Logger.getLogger(LeaderboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);
    }

    private void saveLeaderboard() throws IOException {
        File file = new File(leaderboardFilename);
        file.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(file, false); 
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(model.getDataVector());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMainMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboard = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnMainMenu.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusPainted(false);
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        leaderboard.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        leaderboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Score", "Lines", "Rank", "Level", "Avg. Speed", "Avg. Efficiency"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaderboard.setFocusable(false);
        leaderboard.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(leaderboard);
        if (leaderboard.getColumnModel().getColumnCount() > 0) {
            leaderboard.getColumnModel().getColumn(0).setResizable(false);
            leaderboard.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        Launcher.playSelect();
        setVisible(false);
        Launcher.showStartup();
    }//GEN-LAST:event_btnMainMenuActionPerformed

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
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LeaderboardForm("Tetris Leaderboard").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboard;
    // End of variables declaration//GEN-END:variables

    private void initTableData() {
        if (Files.exists(Path.of(leaderboardFilename))) {
            Vector columnIdentifiers = new Vector();
            columnIdentifiers.add("Player");
            columnIdentifiers.add("Score");
            columnIdentifiers.add("Lines");
            columnIdentifiers.add("Rank");
            columnIdentifiers.add("Level");
            columnIdentifiers.add("Avg. Speed");
            columnIdentifiers.add("Avg. Efficiency");

            try (FileInputStream fis = new FileInputStream(leaderboardFilename); ObjectInputStream ois = new ObjectInputStream(fis)) {
                model.setDataVector((Vector<? extends Vector>) ois.readObject(), columnIdentifiers);

            } catch (Exception ex) {
                Logger.getLogger(LeaderboardForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
            centerRend.setHorizontalAlignment(JLabel.CENTER);
            leaderboard.setDefaultRenderer(String.class, centerRend);
            leaderboard.setDefaultRenderer(Integer.class, centerRend);
        }
    }

    private void initTableSorter() {
        sorter = new TableRowSorter<>(model);
        leaderboard.setRowSorter(sorter);
        
        List<SortKey> keys = new ArrayList<>();
        keys.add(new SortKey(1, SortOrder.DESCENDING));
        keys.add(new SortKey(2, SortOrder.DESCENDING));
        keys.add(new SortKey(3, SortOrder.DESCENDING));
        keys.add(new SortKey(4, SortOrder.DESCENDING));
        keys.add(new SortKey(5, SortOrder.DESCENDING));
        keys.add(new SortKey(6, SortOrder.DESCENDING));
        
        sorter.setSortKeys(keys);
    }
}
