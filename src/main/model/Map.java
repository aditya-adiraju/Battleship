package model;

public abstract class Map {
    int size;
    String[][] board;

    Map(int size) {
        this.size = size;
        this.board = new String[size][size];
    }

    public String[][] getBoard() {
        return board;
    }

    // REQUIRES: size > x, y >= 0
    // MODIFIES: this
    // EFFECTS: changes value of square at the given coordinate to given string
    void setCoordinate(String s, int x, int y) {
        this.board[x][y] = s;
    }

}
