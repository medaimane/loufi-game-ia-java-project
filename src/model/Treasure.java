package model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Treasure implements Serializable {
    
    static final long serialVersionUID = 1L;

    private Dimension position;
    private String image;

    public Treasure(Treasure B) {
        this.position = B.getPosition();
        this.image = B.getImage();
    }

    public Treasure(Dimension position, String image) {
        this.position = position;
        this.image = image;
    }

    public Treasure(String image) {
        this.image = image;
        position = new Dimension(0, 0);
    }

    public void setBonus(Treasure B) {
        setPosition(B.getPosition());
        setImage(B.getImage());
    }

    public Dimension getPosition() {
        return position;
    }

    public void setPosition(Dimension position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void generate(int width, int height) {
        position.width = ThreadLocalRandom.current().nextInt(0, width);
        position.height = ThreadLocalRandom.current().nextInt(0, height);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.position);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Treasure other = (Treasure) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return true;
    }

}
