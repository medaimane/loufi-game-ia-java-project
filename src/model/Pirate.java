package model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Pirate implements Serializable {

    static final long serialVersionUID = 1L;

    private Dimension position;
    private Treasure bonus;
    private String image;

    private Dimension[] path;
    private int i = 0;

    public Pirate(Dimension position, Treasure bonus) {
        this.position = position;
        this.bonus = bonus;
    }

    public Pirate(Dimension position, Treasure bonus, String image) {
        this.position = position;
        this.bonus = bonus;
        this.image = image;
    }

    public Pirate(Pirate bot) {
        this.position = bot.position;
        this.bonus = bot.bonus;
        this.image = bot.image;
    }

    public Pirate(String image) {
        this.image = image;
        this.position = new Dimension();
        this.path = null;
        this.path = new Dimension[1000];
    }

    public void setPath(Dimension[] path) {
        i = 0;
        this.path = new Dimension[path.length + 1];
        int k = 0;
        for (int j = path.length - 1; j >= 0; j--) {
            this.path[k] = path[j];
            k++;
        }
    }

    public Dimension getPosition() {
        return this.position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;

    }

    public void setBonus(Treasure bonus) {
        this.bonus = bonus;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public Treasure getBonus() {
        return bonus;
    }

    public void generate(int width, int height) {
        position.width = ThreadLocalRandom.current().nextInt(0, width);
        position.height = ThreadLocalRandom.current().nextInt(0, height);
    }

    public void move() {
        if (i != path.length) {
            this.position.width = path[i].width;
            this.position.height = path[i].height;
            this.i++;
        }
    }

    public Dimension[] getPath() {
        return this.path;
    }

    public void printPath() {
        for (int j = 0; j < path.length; j++) {

            System.out.println(path[j]);

        }
    }

}
