/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import applications.EngineGame;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author medaimane
 */
public class PlayGameFrame extends javax.swing.JFrame implements Serializable {

    static final long serialVersionUID = 1L;

    private OptionsFrame op;
    private AboutGameFrame aboutGame;

    private String playerName;
    private String playerImage;
    private Integer algoIndex;
    private Integer bonnusNumbre;
    private Integer botsNumbre;
    private Integer mazeIndex;

    private Dimension playerPosition;
    private Integer playerPositionWidth;
    private Integer playerPositionhieght;

    // game variable part:
    private EngineGame game;

    // thread for a game :
    public static Thread t;

    /**
     * Creates new form PlayGameFrame
     */
    public PlayGameFrame() {
        this.initComponents();
        this.setInit();
    }

    public PlayGameFrame(String playerName, String playerImage, Integer algoIndex, Integer bonnusNumbre, Integer botsNumbre, Integer mazeIndex) {
        this.initComponents();
        this.setInit(playerName, playerImage, algoIndex, bonnusNumbre, botsNumbre, mazeIndex);
    }

    private void setInit() {
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1, 2));
        this.doAction(this.playPanel);
        this.setPanelDimension(this.playPanel);

        this.algoIndex = 0;
        this.bonnusNumbre = 0;
        this.botsNumbre = 0;
        this.mazeIndex = 0;
        this.playerImage = "images/player1.jpg";

        this.setInitGame(this.playerImage, this.algoIndex, this.bonnusNumbre, this.botsNumbre, this.mazeIndex, 10, 10);
    }

    private void setInit(String playerName, String playerImage, Integer algoIndex, Integer bonnusNumbre, Integer botsNumbre, Integer mazeIndex) {
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1, 2));

        this.setPanelDimension(this.playPanel);

        this.playerName = playerName;
        this.algoIndex = algoIndex;
        this.bonnusNumbre = bonnusNumbre;
        this.botsNumbre = botsNumbre;
        this.mazeIndex = mazeIndex;
        this.playerImage = playerImage;
        this.playerNameLabel.setText(playerName);

        this.setAddPictureLabel(this.playerImagejLabel, this.getResizedImage(playerImage, this.playerImagejLabel.getWidth(), this.playerImagejLabel.getHeight()));
        this.setAddPictureLabel(this.treasorImagejLabel, this.getResizedImage("images/tresor1.jpg", this.treasorImagejLabel.getWidth(), this.treasorImagejLabel.getHeight()));

        int confirmation = JOptionPane.showConfirmDialog(null,
                "Play with this Options :\nPlayer Name : " + playerName + "\nPlayer position : (w = 0,h = 0)" + "\nAlgorithm : " + algoIndex + "\nNumbre of bonus : " + bonnusNumbre + "\nNumber of bots : " + botsNumbre + "\nMaze numbre : " + mazeIndex,
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            this.setInitGame(playerImage, algoIndex, bonnusNumbre, botsNumbre, mazeIndex, 10, 10);
        } else {
            this.dispose();
            this.op = new OptionsFrame(playerName, playerImage, algoIndex, bonnusNumbre, botsNumbre, mazeIndex);
        }
        this.doAction(this.playPanel);
    }

    // New modification :
    private void setInit(String playerName, Dimension playerPosition, String playerImage, Integer algoIndex, Integer bonnusNumbre, Integer botsNumbre, Integer mazeIndex) {
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1, 2));

        this.setPanelDimension(this.playPanel);

        this.playerName = playerName;
        this.algoIndex = algoIndex;
        this.bonnusNumbre = bonnusNumbre;
        this.botsNumbre = botsNumbre;
        this.mazeIndex = mazeIndex;
        this.playerImage = playerImage;
        this.playerNameLabel.setText(playerName);
        
        this.playerPosition = playerPosition;

        this.setAddPictureLabel(this.playerImagejLabel, this.getResizedImage(playerImage, this.playerImagejLabel.getWidth(), this.playerImagejLabel.getHeight()));
        this.setAddPictureLabel(this.treasorImagejLabel, this.getResizedImage("images/tresor1.jpg", this.treasorImagejLabel.getWidth(), this.treasorImagejLabel.getHeight()));

        int confirmation = JOptionPane.showConfirmDialog(null,
                "Play with this Options :\nPlayer Name : " + playerName + "\nPlayer position : (w = 0,h = 0)" + "\nAlgorithm : " + algoIndex + "\nNumbre of bonus : " + bonnusNumbre + "\nNumber of bots : " + botsNumbre + "\nMaze numbre : " + mazeIndex,
                "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            this.setInitGame(playerImage, algoIndex, bonnusNumbre, botsNumbre, mazeIndex, 10, 10);
        } else {
            this.dispose();
            this.op = new OptionsFrame(playerName, playerImage, algoIndex, bonnusNumbre, botsNumbre, mazeIndex);
        }
        this.doAction(this.playPanel);
    }

    //
    private void setInitGame(String playerImage, Integer algoIndex, Integer bonnusNumbre, Integer botsNumbre, Integer mazeIndex, int width, int height) {
        this.game = new EngineGame(playerImage, new Dimension(0, 0), algoIndex, bonnusNumbre, botsNumbre, mazeIndex, width, height);
        this.game.setBackground(this.playPanel.getBackground());
        this.game.setFocusable(true);

        t = new Thread(this.game);
        t.start();

        this.game.setSize(new Dimension(this.playPanel.getWidth(), this.playPanel.getHeight()));
        this.playPanel.add(this.game);
        scoreValueLabel.setText(0000 + "");
        this.doAction(this.game);
    }

    private void setInitGame(EngineGame game, int score, String doAction) {
        this.game = game;
        startPauseButton.setText(doAction);
        //this.game.doDetectMove();
        this.game.setBackground(this.playPanel.getBackground());
        this.game.setFocusable(true);

        t = new Thread(this.game);
        t.start();

        this.game.setSize(new Dimension(this.playPanel.getWidth(), this.playPanel.getHeight()));
        this.playPanel.add(this.game);
        scoreValueLabel.setText(score + "");
        this.doAction(this.game);
    }

    private void doAction(JPanel game) {
        game.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent ev) {
                game.requestFocus();
            }
        });
    }

    // resized image :
    // Set resized image for a lable  :
    private void setAddPictureLabel(javax.swing.JLabel label, Image image) {
        label.setIcon(new ImageIcon(resizeImage(
                new ImageIcon(image).getImage(),
                label.getWidth(),
                label.getHeight()
        )));
    }

    private Image resizeImage(Image img, int w, int h) {
        BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = resizedImage.createGraphics();
        g2D.drawImage(img, 0, 0, w, h, null);
        g2D.dispose();

        return resizedImage;
    }

    // get image :
    private Image getResizedImage(String pathfile, int w, int h) {
        Image imguser = null;
        try {
            imguser = ImageIO.read(new File(pathfile));
        } catch (IOException ex) {
            // 
        }
        imguser = resizeImage(imguser, w, h);

        return imguser;
    }

    // Set text field the default Dimension :
    private void setPanelDimension(javax.swing.JPanel JP) {
        JP.setPreferredSize(new Dimension(JP.getWidth(), JP.getHeight()));
    }

    public Integer getAlgoIndex() {
        return algoIndex;
    }

    public void setAlgoIndex(Integer algoIndex) {
        this.algoIndex = algoIndex;
    }

    public Integer getBonnusNumbre() {
        return bonnusNumbre;
    }

    public void setBonnusNumbre(Integer bonnusNumbre) {
        this.bonnusNumbre = bonnusNumbre;
    }

    public Integer getBotsNumbre() {
        return botsNumbre;
    }

    public void setBotsNumbre(Integer botsNumbre) {
        this.botsNumbre = botsNumbre;
    }

    public Integer getMazeIndex() {
        return mazeIndex;
    }

    public void setMazeIndex(Integer mazeIndex) {
        this.mazeIndex = mazeIndex;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public EngineGame getGame() {
        return game;
    }

    public void setGame(EngineGame game) {
        this.game = game;
    }

    public void hidden() {
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        playPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scoreValueLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        startPauseButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        treasorImagejLabel = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        playerNameLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        playerImagejLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Play Game");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        playPanel.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout playPanelLayout = new javax.swing.GroupLayout(playPanel);
        playPanel.setLayout(playPanelLayout);
        playPanelLayout.setHorizontalGroup(
            playPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        playPanelLayout.setVerticalGroup(
            playPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Score");

        scoreValueLabel.setForeground(new java.awt.Color(255, 204, 51));
        scoreValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreValueLabel.setText("value");

        exitButton.setBackground(new java.awt.Color(255, 102, 0));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        optionsButton.setBackground(new java.awt.Color(255, 102, 0));
        optionsButton.setText("Options");
        optionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonActionPerformed(evt);
            }
        });

        loadButton.setBackground(new java.awt.Color(51, 51, 255));
        loadButton.setForeground(new java.awt.Color(255, 255, 255));
        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(51, 51, 255));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        startPauseButton.setBackground(new java.awt.Color(51, 153, 255));
        startPauseButton.setForeground(new java.awt.Color(255, 255, 255));
        startPauseButton.setText("Break");
        startPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPauseButtonActionPerformed(evt);
            }
        });

        treasorImagejLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        playerNameLabel.setForeground(new java.awt.Color(51, 51, 255));
        playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerNameLabel.setText("Player 01");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(playerImagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jSeparator4))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startPauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator5)
                    .addComponent(playerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(scoreValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(treasorImagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerImagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(treasorImagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startPauseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPauseButtonActionPerformed
        if (startPauseButton.getText().equalsIgnoreCase("Start")) {
            this.setInitGame(this.game, Integer.parseInt(scoreValueLabel.getText()), playerName);
            startPauseButton.setText("Break");
        } else if (startPauseButton.getText().equalsIgnoreCase("Break")) {
            startPauseButton.setText("Start");
        }
    }//GEN-LAST:event_startPauseButtonActionPerformed

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Update the options !", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            this.op = new OptionsFrame(this.playerName, this.playerImage, this.algoIndex, this.bonnusNumbre, this.botsNumbre, this.mazeIndex);
            this.dispose();
            this.op.setVisible(true);
        }
    }//GEN-LAST:event_optionsButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Save !", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                FileOutputStream fileOut = new FileOutputStream("game.dat");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(game);
                out.close();
                fileOut.close();
            } catch (FileNotFoundException ex) {
                System.out.println("" + ex);
            } catch (IOException ex) {
                System.out.println("" + ex);
            } catch (Exception ex) {
                System.out.println("" + ex);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Load !", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            try (
                    FileInputStream fileIn = new FileInputStream("game.dat");
                    ObjectInputStream in = new ObjectInputStream(fileIn)) {
                this.game = (EngineGame) in.readObject();
                setInitGame(this.game, Integer.parseInt(scoreValueLabel.getText()), startPauseButton.getText());
                PlayGameFrame.t.interrupt();
                in.close();
                fileIn.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlayGameFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(PlayGameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Exit !", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PlayGameFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel playPanel;
    private javax.swing.JLabel playerImagejLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JButton saveButton;
    public static javax.swing.JLabel scoreValueLabel;
    public static javax.swing.JButton startPauseButton;
    private javax.swing.JLabel treasorImagejLabel;
    // End of variables declaration//GEN-END:variables
}
