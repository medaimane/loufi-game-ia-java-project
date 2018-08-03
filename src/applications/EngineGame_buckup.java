package applications;

import model.Maze;
import model.Pirate;
import model.Treasure;
import model.Player;
import algorithms.AbstractSearchEngine;
import algorithms.BreadthFirstSearchEngine;
import algorithms.DepthFirstSearchEngine;
import algorithms.AStarSearchEngine;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import interfaces.PlayGameFrame;
import java.io.Serializable;

public class EngineGame_buckup extends JPanel implements Runnable, Serializable {

    static final long serialVersionUID = 1L;

    AbstractSearchEngine algoritme;

    private Integer algoIndex;
    private Integer mazeIndex;
    private Integer bonusNum;
    private Integer botsNum;
    private String playerImage;

    private String bonusImage = "images/tresor1.jpg";
    private String BotsImage = "images/pirate1.jpg";

    private Maze theMap;
    private Player player;
    private Pirate[] bots;
    private Treasure[] bonuses;
    
    Dimension[] path = null;

    private final String[] images = {"0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png", "10.png", "11.png", "12.png", "13.png", "14.png"};

    public EngineGame_buckup() {
        this.theMap = new Maze(1, 10, 10);
        this.algoIndex = 0;
        this.mazeIndex = 1;
        this.bonuses = new Treasure[5];
        this.bots = new Pirate[3];
        this.playerImage = "images/player1.jpg";

        detectMove();
        play();
    }

    public EngineGame_buckup(String playerImage, Integer mazeIndex, int width, int height) {
        this.theMap = new Maze(mazeIndex, width, height);
        this.algoIndex = 0;
        this.mazeIndex = 1;
        this.bonuses = new Treasure[5];
        this.bots = new Pirate[3];
        this.playerImage = playerImage;

        detectMove();
        play();
    }

    public EngineGame_buckup(String playerImage, Integer algoIndex, Integer bonnusNum, Integer botsNum, Integer mazeIndex, int width, int height) {
        this.theMap = new Maze(mazeIndex, width, height);
        this.algoIndex = algoIndex;
        this.mazeIndex = mazeIndex;
        this.bonusNum = bonnusNum;
        this.botsNum = botsNum;
        this.playerImage = playerImage;

        this.bonuses = new Treasure[this.bonusNum];
        this.bots = new Pirate[this.botsNum];

        detectMove();
        play();
    }

    private Treasure getRandomBonus() {
        int i = ThreadLocalRandom.current().nextInt(0, bonuses.length);
        Treasure b;
        if (bonuses[i] != null) {
            b = new Treasure(bonuses[i]);
        } else {
            b = new Treasure(getRandomBonus());
        }
        return b;
    }

    private void play() {
        player = new Player(new Dimension(0, 0), this.playerImage);

        for (int i = 0; i < bonuses.length; i++) {
            bonuses[i] = new Treasure(this.bonusImage);
            bonuses[i].generate(theMap.getWidth(), theMap.getHeight());
        }
        for (int i = 0; i < bots.length; i++) {
            bots[i] = new Pirate(this.BotsImage);
            bots[i].generate(theMap.getWidth(), theMap.getHeight());
            Treasure b = new Treasure(getRandomBonus());
            if (b.getPosition() != null) {
                bots[i].setBonus(b);
            }
            if (0 != this.algoIndex) {
                switch (this.algoIndex) {
                    case 1:
                        this.algoritme = new DepthFirstSearchEngine(theMap);
                        path = null;
                        path = ((BreadthFirstSearchEngine) algoritme).getSearchPath(bots[i], bots[i].getBonus());
                        if (path != null) {
                            bots[i].setPath(path);
                        } 
//                        else {
//                            bots[i].setPath(((DepthFirstSearchEngine) algoritme).getSearchPath(bots[i], getRandomBonus()));
//                        }
                        break;
                    case 2:
                        this.algoritme = new BreadthFirstSearchEngine(theMap);
                        path = null;
                        path = ((BreadthFirstSearchEngine) algoritme).getSearchPath(bots[i], bots[i].getBonus());
                        if (path != null) {
                            bots[i].setPath(path);
                        } 
//                        else {
//                            bots[i].setPath(((BreadthFirstSearchEngine) algoritme).getSearchPath(bots[i], getRandomBonus()));
//                        }
                        break;
                    case 3:
                        this.algoritme = new AStarSearchEngine(theMap);
                        if (((AStarSearchEngine) algoritme).getSearchPath(bots[i], bots[i].getBonus()) != null) {
                            bots[i].setPath(((AStarSearchEngine) algoritme).getSearchPath(bots[i], bots[i].getBonus()));
                        } else {
                            bots[i].setPath(((AStarSearchEngine) algoritme).getSearchPath(bots[i], getRandomBonus()));
                        }
                        break;
                    default:
                        break;
                }
            }

        }

        repaint();
    }

    public void doDetectMove() {
        detectMove();
    }

    private void detectMove() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int KeyCode = ke.getKeyCode();
                switch (KeyCode) {
                    case KeyEvent.VK_DOWN:
                        if (PlayGameFrame.startPauseButton.getText().equals("Break")) {
                            if (Move(player.getDimension(), "BOTTOM") == 1 && Move(new Dimension(player.getDimension().width, player.getDimension().height + 1), "TOP") == 1) {
                                player.moveDown();
                                collision();
                                eat();
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_UP:
                        if (PlayGameFrame.startPauseButton.getText().equals("Break")) {
                            if (Move(player.getDimension(), "TOP") == 1 && Move(new Dimension(player.getDimension().width, player.getDimension().height - 1), "BOTTOM") == 1) {
                                player.moveUp();
                                collision();
                                eat();
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        if (PlayGameFrame.startPauseButton.getText().equals("Break")) {
                            if (Move(player.getDimension(), "LEFT") == 1 && Move(new Dimension(player.getDimension().width - 1, player.getDimension().height), "RIGHT") == 1) {
                                player.moveLeft();
                                collision();
                                eat();
                            }
                        }
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (PlayGameFrame.startPauseButton.getText().equals("Break")) {
                            if (Move(player.getDimension(), "RIGHT") == 1 && Move(new Dimension(player.getDimension().width + 1, player.getDimension().height), "LEFT") == 1) {
                                player.moveRight();
                                collision();
                                eat();
                            }
                        }
                        repaint();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (PlayGameFrame.startPauseButton.getText().equals("Break")) {
                    for (Pirate bot1 : bots) {
                        for (Treasure bonuse : bonuses) {
                            if (bonuse.getPosition().equals(bot1.getPosition())) {
                                Dimension b = new Dimension(bonuse.getPosition());
                                bonuse.generate(theMap.getWidth(), theMap.getHeight());
                                for (Pirate bot2 : bots) {
                                    if (bot2.getPosition().equals(b)) {
                                        Pirate bot = new Pirate(bot2);

                                        if (0 != this.algoIndex) {
                                            switch (this.algoIndex) {
                                                case 1:
                                                    if (((DepthFirstSearchEngine) algoritme).getSearchPath(bot, bot.getBonus()) != null) {
                                                        bot2.setPath(((DepthFirstSearchEngine) algoritme).getSearchPath(bot2, bot.getBonus()));
                                                    } else {
                                                        bot2.setPath(((DepthFirstSearchEngine) algoritme).getSearchPath(bot2, getRandomBonus()));
                                                    }
                                                    break;
                                                case 2:
                                                    if (((BreadthFirstSearchEngine) algoritme).getSearchPath(bot, bot.getBonus()) != null) {
                                                        bot2.setPath(((BreadthFirstSearchEngine) algoritme).getSearchPath(bot2, bot.getBonus()));
                                                    } else {
                                                        bot2.setPath(((BreadthFirstSearchEngine) algoritme).getSearchPath(bot2, getRandomBonus()));
                                                    }
                                                    break;
                                                case 3:
                                                    if (((AStarSearchEngine) algoritme).getSearchPath(bot, bot.getBonus()) != null) {
                                                        bot2.setPath(((AStarSearchEngine) algoritme).getSearchPath(bot2, bot.getBonus()));
                                                    } else {
                                                        bot2.setPath(((AStarSearchEngine) algoritme).getSearchPath(bot2, getRandomBonus()));
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                        bot1.move();
                        collision();
                    }
                    repaint();
                    Thread.sleep(500);
                } else {
                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException v) {
            System.out.println(v);
        }
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        int width = theMap.getWidth();
        int height = theMap.getHeight();

        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);

        java.awt.Graphics g2 = image.getGraphics();

        g2.fillRect(0, 0, 300, 300);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                short val = theMap.getValue(x, y);

                for (Treasure bonuse : bonuses) {
                    if (bonuse.getPosition().width == x && bonuse.getPosition().height == y) {
                        Image imageAP = getResizedImage(bonuse.getImage(), 29, 29);
                        g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                    }
                }

                for (Pirate bot : bots) {
                    if (bot.getPosition().width == x && bot.getPosition().height == y) {
                        Image imageAP = getResizedImage(bot.getImage(), 29, 29);
                        g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                    }
                }

                if (player.getDimension().width == x && player.getDimension().height == y) {
                    Image imageAP = getResizedImage(player.getImage(), 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                }

                if (val == Maze.EMPTY) {
                    //Image G = getResizedImage(images[0], 29, 29);
                    //g2.drawImage(G, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP) {
                    Image imageAP = getResizedImage(images[1], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.LEFT) {
                    Image LEFT = getResizedImage(images[2], 29, 29);
                    g2.drawImage(LEFT, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.BOTTOM) {
                    Image imageAP = getResizedImage(images[3], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.RIGHT) {
                    Image imageAP = getResizedImage(images[4], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_LEFT) {
                    Image imageAP = getResizedImage(images[5], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_BOTTOM) {
                    Image imageAP = getResizedImage(images[6], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_RIGHT) {
                    Image imageAP = getResizedImage(images[7], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.LEFT_RIGHT) {
                    Image imageAP = getResizedImage(images[8], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.LEFT_BOTOM) {
                    Image imageAP = getResizedImage(images[9], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.BOTTOM_RIGHT) {
                    Image imageAP = getResizedImage(images[10], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_LEFT_BOTTOM) {
                    Image imageAP = getResizedImage(images[11], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_RIGHT_BOTTOM) {
                    Image imageAP = getResizedImage(images[12], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.LEFT_BOTTOM_RIGHT) {
                    Image imageAP = getResizedImage(images[13], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                } else if (val == Maze.TOP_LEFT_BOTTOM) {
                    Image imageAP = getResizedImage(images[14], 29, 29);
                    g2.drawImage(imageAP, 6 + x * 29, 3 + y * 29, 29, 29, null);
                }

            }
        }
        g.drawImage(image, 30, 40, 320, 320, null);
    }

    public int Move(Dimension location, String MOVE) {

        switch (MOVE) {
            case "TOP":
                if (theMap.getValue(location.width, location.height) == Maze.TOP
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM) {

                    return 0;

                } else {
                    return 1;
                }
            case "BOTTOM":
                if (theMap.getValue(location.width, location.height) == Maze.BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.BOTTOM_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_BOTOM
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.TOP_BOTTOM) {

                    return 0;

                } else {
                    return 1;
                }
            case "LEFT":
                if (theMap.getValue(location.width, location.height) == Maze.LEFT
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_BOTOM
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT) {

                    return 0;

                } else {
                    return 1;
                }
            default:
                if (theMap.getValue(location.width, location.height) == Maze.RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.BOTTOM_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_RIGHT
                        || theMap.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM
                        || theMap.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT) {

                    return 0;

                } else {
                    return 1;
                }
        }

    }

    public void collision() {
        for (Pirate bot : bots) {
            if (player.getDimension().equals(bot.getPosition())) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Game over\nrestart?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmation == JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else if (confirmation == JOptionPane.YES_OPTION) {
                    PlayGameFrame.scoreValueLabel.setText(00 + "");
                    play();
                }
            }
        }
    }

    public void eat() {
        for (Treasure bonuse : bonuses) {
            if (player.eat(bonuse)) {
                Dimension b = new Dimension(bonuse.getPosition());
                bonuse.generate(theMap.getWidth(), theMap.getHeight());
                for (Pirate bot : bots) {
                    if (bot.getBonus().getPosition().equals(b)) {
                        if (0 != this.algoIndex) {
                            switch (this.algoIndex) {
                                case 1:
                                    bot.setPath(((DepthFirstSearchEngine) algoritme).getSearchPath(bot, this.getRandomBonus()));
                                    break;
                                case 2:
                                    bot.setPath(((BreadthFirstSearchEngine) algoritme).getSearchPath(bot, this.getRandomBonus()));
                                    break;
                                case 3:
                                    bot.setPath(((AStarSearchEngine) algoritme).getSearchPath(bot, this.getRandomBonus()));
                                    break;
                                default:
                                    break;
                            }
                        }

                    }
                }
                PlayGameFrame.scoreValueLabel.setText((Integer.valueOf(PlayGameFrame.scoreValueLabel.getText()) + 100) + "");
            }
        }
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
        }
        imguser = resizeImage(imguser, w, h);

        return imguser;
    }

    public Integer getAlgoIndex() {
        return algoIndex;
    }

    public void setAlgoIndex(Integer algoIndex) {
        this.algoIndex = algoIndex;
    }

    public Integer getMazeIndex() {
        return mazeIndex;
    }

    public void setMazeIndex(Integer mazeIndex) {
        this.mazeIndex = mazeIndex;
    }

    public Integer getBonusNum() {
        return bonusNum;
    }

    public void setBonusNum(Integer bonusNum) {
        this.bonusNum = bonusNum;
    }

    public Integer getBotsNum() {
        return botsNum;
    }

    public void setBotsNum(Integer botsNum) {
        this.botsNum = botsNum;
    }

    public Maze getMap() {
        return theMap;
    }

    public void setMap(Maze theMap) {
        this.theMap = theMap;
    }

}
