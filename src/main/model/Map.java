package model;

import java.util.Arrays;

// CLASS-LEVEL COMMENT:
// An abstract class which represents a map
// creates and initializes a generic nxn board whose coordinates can be set
public abstract class Map {
    // RESOURCE USED: to get extended unicode characters
    // https://stackoverflow.com/a/26575039
    private static final int[] codePoints = {0x2B1C};
    public static final String EMPTY_SQUARE = new String(codePoints, 0, codePoints.length);

    int size;
    String[][] board;


    Map(int size) {
        this.size = size;
        this.board = new String[size][size];
        for (String[] row: this.board) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
    }

    public String[][] getBoard() {
        return board;
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: changes value of square at the given coordinate to given string
    void setCoordinate(String s, int x, int y) {
        this.board[y][x] = s;
    }

}
