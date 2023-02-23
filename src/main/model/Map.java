package model;

import java.util.Arrays;

public abstract class Map {
    static final String EMPTY_SQUARE = " ";

    int size;
    String[][] board;


    Map(int size) {
        this.size = size;
        this.board = new String[size][size];
        for (String[] row: this.board) {
            Arrays.fill(row, " ");
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
