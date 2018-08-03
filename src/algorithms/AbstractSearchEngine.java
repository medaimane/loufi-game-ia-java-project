package algorithms;

/**
 * Title: AbstractSearchEngine<p>
 * Description: Abstract search engine for searching paths in a maze<p>
 * Copyright: Copyright (c) Mark Watson, Released under Open Source Artistic
 * License<p>
 * Company: Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */
import java.awt.Dimension;
import java.io.Serializable;
import model.Treasure;
import model.Pirate;
import model.Maze;

public class AbstractSearchEngine implements Serializable {
    
    static final long serialVersionUID = 1L;

    protected Maze maze;

    public AbstractSearchEngine(Maze maze) {
        this.maze = maze;
    }

    public Maze getMaze() {
        return maze;
    }

    /**
     * We will use the Java type Dimension (fields width and height will encode
     * the coordinates in x and y directions) for the search path:
     */
    protected Dimension[] searchPath = null;
    protected int pathCount;
    protected int maxDepth;
    protected Dimension startLoc, goalLoc, currentLoc;
    protected boolean isSearching = true;

    protected void initSearch(Pirate bot, Treasure bonus) {

        searchPath = new Dimension[1000];
        for (int i = 0; i < 1000; i++) {
            searchPath[i] = new Dimension();
        }

        pathCount = 0;
        startLoc = bot.getPosition();
        currentLoc = startLoc;
        goalLoc = bonus.getPosition();
        searchPath[pathCount++] = currentLoc;
    }

    protected boolean equals(Dimension d1, Dimension d2) {
        return (d1.getWidth() == d2.getWidth()) && (d1.getHeight() == d2.getHeight());
    }

    public Dimension[] getPath() {
        Dimension[] ret = new Dimension[maxDepth];
        for (int i = 0; i < maxDepth; i++) {
            ret[i] = searchPath[i];
        }
        return ret;
    }

    public Dimension[] getPossibleMoves(Dimension location) {

        Dimension tempMoves[] = new Dimension[4];
        tempMoves[0] = tempMoves[1] = tempMoves[2] = tempMoves[3] = null;

        int x = location.width;
        int y = location.height;

        int num = 0;
        if (!(maze.getValue(location.width, location.height) == Maze.TOP
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT
                || maze.getValue(location.width, location.height) == Maze.TOP_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM)
                && !(maze.getValue(location.width, location.height - 1) == Maze.BOTTOM
                || maze.getValue(location.width, location.height - 1) == Maze.BOTTOM_RIGHT
                || maze.getValue(location.width, location.height - 1) == Maze.LEFT_BOTOM
                || maze.getValue(location.width, location.height - 1) == Maze.LEFT_BOTTOM_RIGHT
                || maze.getValue(location.width, location.height - 1) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width, location.height - 1) == Maze.TOP_RIGHT_BOTTOM
                || maze.getValue(location.width, location.height - 1) == Maze.TOP_BOTTOM)) {
            tempMoves[num++] = new Dimension(x, y - 1);
        }
        if (!(maze.getValue(location.width, location.height) == Maze.BOTTOM
                || maze.getValue(location.width, location.height) == Maze.BOTTOM_RIGHT
                || maze.getValue(location.width, location.height) == Maze.LEFT_BOTOM
                || maze.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.TOP_BOTTOM)
                && !(maze.getValue(location.width, location.height) == Maze.TOP
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_LEFT
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_RIGHT
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_BOTTOM
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_LEFT_RIGHT
                || maze.getValue(location.width, location.height + 1) == Maze.TOP_RIGHT_BOTTOM)) {
            tempMoves[num++] = new Dimension(x, y + 1);
        }
        if (!(maze.getValue(location.width, location.height) == Maze.LEFT
                || maze.getValue(location.width, location.height) == Maze.LEFT_BOTOM
                || maze.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT
                || maze.getValue(location.width, location.height) == Maze.LEFT_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT)
                && !(maze.getValue(location.width - 1, location.height) == Maze.RIGHT
                || maze.getValue(location.width - 1, location.height) == Maze.BOTTOM_RIGHT
                || maze.getValue(location.width - 1, location.height) == Maze.LEFT_RIGHT
                || maze.getValue(location.width - 1, location.height) == Maze.TOP_LEFT_RIGHT
                || maze.getValue(location.width - 1, location.height) == Maze.TOP_RIGHT
                || maze.getValue(location.width - 1, location.height) == Maze.TOP_RIGHT_BOTTOM
                || maze.getValue(location.width - 1, location.height) == Maze.LEFT_BOTTOM_RIGHT)) {
            tempMoves[num++] = new Dimension(x - 1, y);
        }
        if (!(maze.getValue(location.width, location.height) == Maze.RIGHT
                || maze.getValue(location.width, location.height) == Maze.BOTTOM_RIGHT
                || maze.getValue(location.width, location.height) == Maze.LEFT_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_LEFT_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_RIGHT
                || maze.getValue(location.width, location.height) == Maze.TOP_RIGHT_BOTTOM
                || maze.getValue(location.width, location.height) == Maze.LEFT_BOTTOM_RIGHT)
                && !(maze.getValue(location.width + 1, location.height) == Maze.LEFT
                || maze.getValue(location.width + 1, location.height) == Maze.LEFT_BOTOM
                || maze.getValue(location.width + 1, location.height) == Maze.LEFT_BOTTOM_RIGHT
                || maze.getValue(location.width + 1, location.height) == Maze.LEFT_RIGHT
                || maze.getValue(location.width + 1, location.height) == Maze.TOP_LEFT
                || maze.getValue(location.width + 1, location.height) == Maze.TOP_LEFT_BOTTOM
                || maze.getValue(location.width + 1, location.height) == Maze.TOP_LEFT_RIGHT)) {
            tempMoves[num++] = new Dimension(x + 1, y);
        }

        return tempMoves;
    }

}
