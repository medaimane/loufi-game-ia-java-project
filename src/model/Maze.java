package model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {

    static final long serialVersionUID = 1L;
    
    private int width;
    private int height;
    private Integer mazeIndex;

    private ArrayList<short[][]> mapsArrayList;

    // Mazes :
    // 1° :
    private short[][] map1 = {
        {-5, -1, -6, -1, -6, -6, -1, -1, -6, -7},
        {- 8, - 3, - 4, 0, 0, - 4, - 4, - 3, - 4, - 4},
        {- 8, 0, 0, - 3, - 10, - 4, - 3, - 4, - 4, - 4},
        {- 8, - 4, - 10, 0, 0, - 3, - 10, - 3, 0, - 4},
        {- 8, - 3, - 3, - 10, 0, 0, - 3, - 3, - 10, - 4},
        {- 9, 0, 0, - 3, 0, 0, 0, 0, - 3, - 10},
        {- 2, - 3, - 3, - 4, 0, - 3, - 4, - 3, - 3, - 4},
        {- 2, - 3, - 3, - 3, - 4, - 3, - 10, 0, - 3, - 4},
        {- 9, - 3, - 4, 0, 0, 0, - 3, - 4, - 4, - 4},
        {- 9, - 3, - 3, - 3, - 10, - 10, - 3, - 10, - 3, - 10}
    };

    // 2° :
    private short[][] map2 = {
        {-5, - 12, - 1, - 1, - 1, - 12, - 1, - 6, - 6, - 7},
        {- 8, 0, - 3, 0, - 3, 0, - 10, 0, - 3, - 4},
        {- 2, - 10, 0, - 4, - 3, - 4, 0, - 10, 0, - 4},
        {- 9, 0, - 4, - 3, - 3, - 4, - 4, 0, - 10, - 4},
        {- 8, 0, - 4, 0, - 10, - 4, - 3, - 3, 0, - 10},
        {- 9, 0, 0, - 3, - 4, - 3, - 3, - 3, - 3, - 4},
        {- 2, - 3, - 3, - 4, - 3, 0, 0, 0, - 3, - 10},
        {- 8, 0, - 3, - 3, - 3, 0, - 10, - 3, - 4, - 4},
        {- 8, 0, - 3, - 3, - 3, - 10, 0, - 4, - 3, - 4},
        {- 9, - 3, - 3, - 3, - 3, - 3, - 10, - 3, - 3, - 10}
    };

    // 3° :
    private short[][] map3 = {
        {- 5, - 7, - 1, - 7, - 1, - 6, - 6, - 6, - 1, - 7},
        {- 2, - 10, - 4, - 4, - 4, 0, - 3, - 3, - 10, - 4},
        {- 8, 0, - 10, - 3, - 4, - 3, - 3, - 3, 0, - 4},
        {- 8, - 3, - 3, - 4, - 4, - 3, - 4, 0, - 10, - 4},
        {- 9, 0, - 3, - 4, - 3, - 4, - 3, - 4, - 4, - 4},
        {- 9, - 3, - 3, 0, - 3, - 3, - 4, - 10, 0, - 10},
        {- 2, - 4, - 3, - 10, 0, - 3, - 10, 0, - 10, - 4},
        {- 13, 0, - 3, - 3, - 10, 0, 0, - 10, 0, - 4},
        {- 2, - 3, - 10, 0, - 4, - 10, - 4, 0, - 10, - 4},
        {- 9, - 3, - 3, - 10, - 3, - 3, - 3, - 10, -3, - 10}
    };

    private short[][] map4 = {
        {- 5, - 7, - 6, - 6, - 1, - 7, - 1, - 7, - 6, - 7},
        {- 2, - 3, - 3, - 3, - 3, - 4, 0, - 3, - 3, - 4},
        {- 9, - 4, - 4, 0, - 4, 0, - 10, 0, 0, - 10},
        {- 2, - 10, - 4, - 4, 0, - 3, 0, - 10, - 3, - 4},
        {- 2, - 3, - 3, - 4, - 3, - 4, - 10, 0, - 4, - 4},
        {- 9, - 3, - 4, 0, - 3, - 10, 0, - 10, - 3, - 4},
        {- 2, - 3, - 3, - 3, - 3, - 3, - 10, 0, - 3, - 4},
        {- 2, - 4, 0, - 3, - 3, - 3, - 4, - 4, - 3, - 10},
        {- 8, - 3, - 10, 0, - 3, - 3, - 3, - 3, 0, - 4},
        {- 9, - 3, - 3, - 3, - 10, - 3, - 3, - 3, - 10, - 10}

    };

    public Maze(Integer mazeIndex, int width, int height) {

        mapsArrayList = new ArrayList<>(4);
        mapsArrayList.add(this.traspose(map1));
        mapsArrayList.add(this.traspose(map2));
        mapsArrayList.add(this.traspose(map3));
        mapsArrayList.add(this.traspose(map4));

        this.mazeIndex = mazeIndex;

        this.width = width;
        this.height = height;
    }

    synchronized public void setValue(int x, int y, short value) {
        if (null != this.mazeIndex) {
            switch (this.mazeIndex) {
                case 1:
                    map1[x][y] = value;
                    break;
                case 2:
                    map2[x][y] = value;
                    break;
                case 3:
                    map3[x][y] = value;
                    break;
                case 4:
                    map4[x][y] = value;
                    break;
                default:
                    break;
            }
        }
    }

    synchronized public void setValue(Dimension P, short value) {
        if (null != this.mazeIndex) {
            switch (this.mazeIndex) {
                case 1:
                    map1[P.width][P.height] = value;
                    break;
                case 2:
                    map2[P.width][P.height] = value;
                    break;
                case 3:
                    map3[P.width][P.height] = value;
                    break;
                case 4:
                    map4[P.width][P.height] = value;
                    break;
                default:
                    break;
            }
        }
    }

    synchronized public short getValue(int x, int y) {
        short m = 0;
        if (null != this.mazeIndex) {
            switch (this.mazeIndex) {
                case 1:
                    m = mapsArrayList.get(0)[x][y];
                    break;
                case 2:
                    m = mapsArrayList.get(1)[x][y];
                    break;
                case 3:
                    m = mapsArrayList.get(2)[x][y];
                    break;
                case 4:
                    m = mapsArrayList.get(3)[x][y];
                    break;
                default:
                    break;
            }
        }
        return m;
    }

    synchronized public short getValue(Dimension P) {
        short m = 0;
        if (null != this.mazeIndex) {
            switch (this.mazeIndex) {
                case 1:
                    m = mapsArrayList.get(0)[P.width][P.height];
                    break;
                case 2:
                    m = mapsArrayList.get(1)[P.width][P.height];
                    break;
                case 3:
                    m = mapsArrayList.get(2)[P.width][P.height];
                    break;
                case 4:
                    m = mapsArrayList.get(3)[P.width][P.height];
                    break;
                default:
                    break;
            }
        }
        return m;
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map1[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ArrayList<short[][]> getMapsArrayList() {
        return mapsArrayList;
    }

    public void setMapsArrayList(ArrayList<short[][]> mapsArrayList) {
        this.mapsArrayList = mapsArrayList;
    }

    private short[][] traspose(short[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        short[][] trasposedMatrix = new short[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                trasposedMatrix[x][y] = matrix[y][x];
            }
        }

        return trasposedMatrix;
    }

    public static short BOT = 3;
    public static short BONUS = 2;
    public static short PLAYER = 1;
    public static short EMPTY = 0;
    public static short TOP = -1;
    public static short LEFT = -2;
    public static short BOTTOM = -3;
    public static short RIGHT = -4;
    public static short TOP_LEFT = -5;
    public static short TOP_BOTTOM = -6;
    public static short TOP_RIGHT = -7;
    public static short LEFT_RIGHT = -8;
    public static short LEFT_BOTOM = -9;
    public static short BOTTOM_RIGHT = -10;
    public static short TOP_LEFT_BOTTOM = -11;
    public static short TOP_RIGHT_BOTTOM = -12;
    public static short LEFT_BOTTOM_RIGHT = -13;
    public static short TOP_LEFT_RIGHT = -14;

}
