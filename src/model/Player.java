package model;

import java.awt.Dimension;
import java.io.Serializable;

public class Player implements Serializable {

    static final long serialVersionUID = 1L;
    
    private Dimension position;
    private String image;

    public Player(Dimension position, String image) {
        this.position = position;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void moveUp() {
        position.height = position.height - 1;
    }

    public void moveLeft() {
        position.width = position.width - 1;
    }

    public void moveDown() {
        position.height = position.height + 1;
    }

    public void moveRight() {
        position.width = position.width + 1;
    }

    public Dimension getDimension() {
        return this.position;
    }

    public boolean eat(Treasure bonus) {
        return position.equals(bonus.getPosition());
    }

}
