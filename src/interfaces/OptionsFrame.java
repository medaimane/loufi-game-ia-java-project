/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author medaimane
 */
public class OptionsFrame extends javax.swing.JFrame {
    
    /**
     *
     * MY Attributes :
     *
     */
    private PlayGameFrame playFrame;

    private String playerName;
    private String playerImage;
    private Dimension playerPosition;
    private Integer playerPositionWidth;
    private Integer playerPositionhieght;
    
    private Integer algoIndex;
    private Integer bonnusNumbre;
    private Integer botsNumbre;
    private Integer mazeIndex;

    /**
     * Creates new form FirstFrame
     */
    public OptionsFrame() {
        this.initComponents();
        this.setInit();
    }

    public OptionsFrame(String playerName, String playerImage) {
        this.initComponents();
        this.setInit(playerName, playerImage, 0, 1, 1, 0);
        this.playerName = playerName;
        this.playerImage = playerImage;
    }

    public OptionsFrame(String playerName,String playerImage, Integer algoIndex, Integer bonusNum, Integer botsNum, Integer mazeIndex) {
        this.initComponents();
        this.setInit(playerName, playerImage, algoIndex, bonusNum, botsNum, mazeIndex);
    }

    /**
     * Create init :
     * 
     * 
     */
    private void setInit() {
        this.setLocationRelativeTo(null);
        this.setPanelDimension(this.optionsGamePanel);
        this.setPanelDimension(this.optionsPanel);
        
        this.setAddPictureLabel(this.maze1jLabel, this.getResizedImage("images/mazes/mazeIndex1.png", this.maze1jLabel.getWidth(), this.maze1jLabel.getHeight()));
        this.setAddPictureLabel(this.maze2jLabel, this.getResizedImage("images/mazes/mazeIndex2.png", this.maze2jLabel.getWidth(), this.maze2jLabel.getHeight()));
        this.setAddPictureLabel(this.maze3jLabel, this.getResizedImage("images/mazes/mazeIndex3.png", this.maze3jLabel.getWidth(), this.maze3jLabel.getHeight()));
        this.setAddPictureLabel(this.maze4jLabel, this.getResizedImage("images/mazes/mazeIndex4.png", this.maze4jLabel.getWidth(), this.maze4jLabel.getHeight()));

        this.algoIndex = 0;
        this.bonnusNumbre = 1;
        this.botsNumbre = 1;
        this.mazeIndex = 0;
        this.playerImage = "images/player1.jpg";

        this.algorithmeComboBox.setSelectedIndex(this.algoIndex);
        this.bonnusjSpinner.setValue(this.bonnusNumbre);
        this.botsjSpinner.setValue(this.botsNumbre);
        switch (this.mazeIndex) {
            case 1:
                this.mazebuttonGroup.setSelected(this.maze1RadioButton.getModel(), true);
                this.maze1RadioButton.setSelected(true);
                break;
            case 2:
                this.mazebuttonGroup.setSelected(this.maze2jRadioButton.getModel(), true);
                break;
            case 3:
                this.mazebuttonGroup.setSelected(this.maze3jRadioButton.getModel(), true);
                break;
            case 4:
                this.mazebuttonGroup.setSelected(this.maze4jRadioButton.getModel(), true);
                break;
            default:
                break;
        }
    }

    private void setInit(String playerName, String playerImage, Integer algoIndex, Integer bonnusNumbre, Integer botsNumbre, Integer mazeIndex) {
        this.setLocationRelativeTo(null);

        this.setPanelDimension(this.optionsPanel);

        this.setAddPictureLabel(this.maze1jLabel, this.getResizedImage("images/mazes/mazeIndex1.png", this.maze1jLabel.getWidth(), this.maze1jLabel.getHeight()));
        this.setAddPictureLabel(this.maze2jLabel, this.getResizedImage("images/mazes/mazeIndex2.png", this.maze2jLabel.getWidth(), this.maze2jLabel.getHeight()));
        this.setAddPictureLabel(this.maze3jLabel, this.getResizedImage("images/mazes/mazeIndex3.png", this.maze3jLabel.getWidth(), this.maze3jLabel.getHeight()));
        this.setAddPictureLabel(this.maze4jLabel, this.getResizedImage("images/mazes/mazeIndex4.png", this.maze4jLabel.getWidth(), this.maze4jLabel.getHeight()));

        if (!playerName.equalsIgnoreCase("")) {
            this.playerName = playerName;
        } else {
            this.playerName = "Player 01";
        }

        this.playerImage = playerImage;
        this.setAddPictureLabel(this.playerImagejLabel, this.getResizedImage(playerImage, this.playerImagejLabel.getWidth(), this.playerImagejLabel.getHeight()));

        this.algoIndex = algoIndex;
        this.bonnusNumbre = bonnusNumbre;
        this.botsNumbre = botsNumbre;
        this.mazeIndex = mazeIndex;

        this.playerNameLabel.setText(" " + this.playerName);
        this.algorithmeComboBox.setSelectedIndex(this.algoIndex);
        this.bonnusjSpinner.setValue(this.bonnusNumbre);
        this.botsjSpinner.setValue(this.botsNumbre);
        switch (mazeIndex) {
            case 1:
                this.mazebuttonGroup.setSelected(this.maze1RadioButton.getModel(), true);
                this.maze1RadioButton.setSelected(true);
                break;
            case 2:
                this.mazebuttonGroup.setSelected(this.maze2jRadioButton.getModel(), true);
                break;
            case 3:
                this.mazebuttonGroup.setSelected(this.maze3jRadioButton.getModel(), true);
                break;
            case 4:
                this.mazebuttonGroup.setSelected(this.maze4jRadioButton.getModel(), true);
                break;
            default:
                break;
        }

    }

    // Set text field the default Dimension :
    private void setPanelDimension(javax.swing.JPanel JP) {
        JP.setPreferredSize(new Dimension(JP.getWidth(), JP.getHeight()));
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

    // setter & getter part, begin :
    public void setAlgoIndex(Integer algoIndex) {
        this.algoIndex = algoIndex;
    }

    public void setBonnusNumbre(Integer bonnusNumbre) {
        this.bonnusNumbre = bonnusNumbre;
    }

    public void setBotsNumbre(Integer botsNumbre) {
        this.botsNumbre = botsNumbre;
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

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    // setter & getter part, end.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mazebuttonGroup = new javax.swing.ButtonGroup();
        optionsGamePanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();
        algorithmeLabel = new javax.swing.JLabel();
        bonnusjLabel = new javax.swing.JLabel();
        algorithmeComboBox = new javax.swing.JComboBox<>();
        botsjLabel = new javax.swing.JLabel();
        bonnusjSpinner = new javax.swing.JSpinner();
        botsjSpinner = new javax.swing.JSpinner();
        grilleChoixPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        maze1jLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        maze2jLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        maze3jLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        maze4jLabel = new javax.swing.JLabel();
        maze1RadioButton = new javax.swing.JRadioButton();
        maze2jRadioButton = new javax.swing.JRadioButton();
        maze3jRadioButton = new javax.swing.JRadioButton();
        maze4jRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        hPlayerPositionjSpinner = new javax.swing.JSpinner();
        wPlayerPositionjSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        returnButton = new javax.swing.JButton();
        startGameButton = new javax.swing.JButton();
        playerImagejLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Options");

        optionsGamePanel.setBackground(new java.awt.Color(204, 204, 255));

        optionsPanel.setBackground(new java.awt.Color(153, 153, 255));
        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        algorithmeLabel.setText("Algorithms :");

        bonnusjLabel.setText("Numbre of bonnus :");

        algorithmeComboBox.setBackground(new java.awt.Color(204, 204, 255));
        algorithmeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- choose --", "1 -> DFS", "2 -> BFS", "3 -> A*" }));
        algorithmeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmeComboBoxActionPerformed(evt);
            }
        });

        botsjLabel.setText("Numbre of bots :");

        bonnusjSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));
        bonnusjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bonnusjSpinnerStateChanged(evt);
            }
        });

        botsjSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        botsjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                botsjSpinnerStateChanged(evt);
            }
        });

        grilleChoixPanel.setBackground(new java.awt.Color(102, 102, 255));
        grilleChoixPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze1jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(maze1jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        maze2jLabel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze2jLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze2jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze3jLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze3jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze4jLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(maze4jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );

        maze1RadioButton.setBackground(new java.awt.Color(102, 102, 255));
        mazebuttonGroup.add(maze1RadioButton);
        maze1RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maze1RadioButtonActionPerformed(evt);
            }
        });

        maze2jRadioButton.setBackground(new java.awt.Color(102, 102, 255));
        mazebuttonGroup.add(maze2jRadioButton);
        maze2jRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maze2jRadioButtonActionPerformed(evt);
            }
        });

        maze3jRadioButton.setBackground(new java.awt.Color(102, 102, 255));
        mazebuttonGroup.add(maze3jRadioButton);
        maze3jRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maze3jRadioButtonActionPerformed(evt);
            }
        });

        maze4jRadioButton.setBackground(new java.awt.Color(102, 102, 255));
        mazebuttonGroup.add(maze4jRadioButton);
        maze4jRadioButton.setToolTipText("");
        maze4jRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maze4jRadioButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Choose a maze.");

        hPlayerPositionjSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        hPlayerPositionjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hPlayerPositionjSpinnerStateChanged(evt);
            }
        });

        wPlayerPositionjSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        wPlayerPositionjSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                wPlayerPositionjSpinnerStateChanged(evt);
            }
        });

        jLabel4.setText(", h=");

        jLabel5.setText("Player Position: w=");

        javax.swing.GroupLayout grilleChoixPanelLayout = new javax.swing.GroupLayout(grilleChoixPanel);
        grilleChoixPanel.setLayout(grilleChoixPanelLayout);
        grilleChoixPanelLayout.setHorizontalGroup(
            grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grilleChoixPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grilleChoixPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wPlayerPositionjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hPlayerPositionjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(grilleChoixPanelLayout.createSequentialGroup()
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maze1RadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maze2jRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maze3jRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maze4jRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        grilleChoixPanelLayout.setVerticalGroup(
            grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grilleChoixPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(grilleChoixPanelLayout.createSequentialGroup()
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maze1RadioButton)
                            .addComponent(maze2jRadioButton)))
                    .addGroup(grilleChoixPanelLayout.createSequentialGroup()
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maze3jRadioButton)
                            .addComponent(maze4jRadioButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grilleChoixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hPlayerPositionjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wPlayerPositionjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1))
                .addGap(14, 14, 14))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        returnButton.setBackground(new java.awt.Color(255, 102, 0));
        returnButton.setForeground(new java.awt.Color(255, 255, 255));
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        startGameButton.setBackground(new java.awt.Color(51, 51, 255));
        startGameButton.setForeground(new java.awt.Color(255, 255, 255));
        startGameButton.setText("Start");
        startGameButton.setBorderPainted(false);
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Player Image :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerImagejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(playerImagejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(returnButton)
                        .addComponent(startGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(8, 8, 8))
        );

        playerNameLabel.setForeground(new java.awt.Color(51, 51, 255));
        playerNameLabel.setText("Player 01");

        jLabel2.setText("Player Name : ");

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(optionsPanelLayout.createSequentialGroup()
                                .addComponent(algorithmeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(algorithmeComboBox, 0, 114, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(optionsPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bonnusjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bonnusjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botsjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(grilleChoixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bonnusjLabel)
                    .addComponent(bonnusjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerNameLabel)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botsjLabel)
                    .addComponent(botsjSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(algorithmeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(algorithmeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grilleChoixPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout optionsGamePanelLayout = new javax.swing.GroupLayout(optionsGamePanel);
        optionsGamePanel.setLayout(optionsGamePanelLayout);
        optionsGamePanelLayout.setHorizontalGroup(
            optionsGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsGamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        optionsGamePanelLayout.setVerticalGroup(
            optionsGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsGamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionsGamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(optionsGamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        System.out.println(this.mazeIndex);
        playFrame = new PlayGameFrame(this.playerName, this.playerImage, this.algoIndex, this.bonnusNumbre, this.botsNumbre, this.mazeIndex);
        this.dispose();
        this.playFrame.setVisible(true);
    }//GEN-LAST:event_startGameButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.dispose();
        new MainFrame().setVisible(true);
    }//GEN-LAST:event_returnButtonActionPerformed

    private void algorithmeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmeComboBoxActionPerformed
        if (this.algorithmeComboBox.getSelectedIndex() != 0) {
            this.algoIndex = this.algorithmeComboBox.getSelectedIndex();
        }
    }//GEN-LAST:event_algorithmeComboBoxActionPerformed

    private void bonnusjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bonnusjSpinnerStateChanged
        this.bonnusNumbre = (Integer) this.bonnusjSpinner.getValue();
    }//GEN-LAST:event_bonnusjSpinnerStateChanged

    private void botsjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_botsjSpinnerStateChanged
        this.botsNumbre = (Integer) this.botsjSpinner.getValue();
    }//GEN-LAST:event_botsjSpinnerStateChanged

    private void maze1RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maze1RadioButtonActionPerformed
        if (this.mazebuttonGroup.isSelected(this.maze1RadioButton.getModel()) == true) {
            if (this.maze1RadioButton.isSelected()) {
                this.mazeIndex = 1;
            }
        }
    }//GEN-LAST:event_maze1RadioButtonActionPerformed

    private void maze2jRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maze2jRadioButtonActionPerformed
        if (this.mazebuttonGroup.isSelected(this.maze2jRadioButton.getModel()) == true) {
            if (this.maze2jRadioButton.isSelected()) {
                this.mazeIndex = 2;
            }
        }
    }//GEN-LAST:event_maze2jRadioButtonActionPerformed

    private void maze3jRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maze3jRadioButtonActionPerformed
        if (this.mazebuttonGroup.isSelected(this.maze3jRadioButton.getModel()) == true) {
            if (this.maze3jRadioButton.isSelected()) {
                this.mazeIndex = 3;
            }
        }
    }//GEN-LAST:event_maze3jRadioButtonActionPerformed

    private void maze4jRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maze4jRadioButtonActionPerformed
        if (this.mazebuttonGroup.isSelected(this.maze4jRadioButton.getModel()) == true) {
            if (this.maze4jRadioButton.isSelected()) {
                this.mazeIndex = 4;
            }
        }
    }//GEN-LAST:event_maze4jRadioButtonActionPerformed

    private void wPlayerPositionjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_wPlayerPositionjSpinnerStateChanged
        this.playerPositionWidth = (Integer) this.wPlayerPositionjSpinner.getValue();
    }//GEN-LAST:event_wPlayerPositionjSpinnerStateChanged

    private void hPlayerPositionjSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hPlayerPositionjSpinnerStateChanged
        this.playerPositionhieght = (Integer) this.hPlayerPositionjSpinner.getValue();
    }//GEN-LAST:event_hPlayerPositionjSpinnerStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details sMenuBar/download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OptionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OptionsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> algorithmeComboBox;
    private javax.swing.JLabel algorithmeLabel;
    private javax.swing.JLabel bonnusjLabel;
    private javax.swing.JSpinner bonnusjSpinner;
    private javax.swing.JLabel botsjLabel;
    private javax.swing.JSpinner botsjSpinner;
    private javax.swing.JPanel grilleChoixPanel;
    private javax.swing.JSpinner hPlayerPositionjSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton maze1RadioButton;
    private javax.swing.JLabel maze1jLabel;
    private javax.swing.JLabel maze2jLabel;
    private javax.swing.JRadioButton maze2jRadioButton;
    private javax.swing.JLabel maze3jLabel;
    private javax.swing.JRadioButton maze3jRadioButton;
    private javax.swing.JLabel maze4jLabel;
    private javax.swing.JRadioButton maze4jRadioButton;
    private javax.swing.ButtonGroup mazebuttonGroup;
    private javax.swing.JPanel optionsGamePanel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JLabel playerImagejLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton startGameButton;
    private javax.swing.JSpinner wPlayerPositionjSpinner;
    // End of variables declaration//GEN-END:variables
}
