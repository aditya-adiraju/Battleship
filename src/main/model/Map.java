package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Arrays;

// CLASS-LEVEL COMMENT:
// An abstract class which represents a map
// creates and initializes a generic nxn board whose coordinates can be set
public abstract class Map implements Writable {
    // RESOURCE USED: to get extended unicode characters
    // https://stackoverflow.com/a/26575039
    private static final int[] codePoints = {0x2B1C};
    public static final String EMPTY_SQUARE = new String(codePoints, 0, codePoints.length);

    int size;
    String[][] board;

    // EFFECTS: create a new Map object with size of size X size.
    Map(int size) {
        this.size = size;
        this.board = new String[size][size];
        for (String[] row: this.board) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: changes value of square at the given coordinate to given string
    public void setCoordinate(String s, int x, int y) {
        this.board[y][x] = s;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray allRows = new JSONArray();
        JSONArray oneRow;
        for (String[] row : board) {
            oneRow = new JSONArray();
            for (String s : row) {
                oneRow.put(s);
            }
            allRows.put(oneRow);
        }
        json.put("size", size);
        json.put("board", allRows);
        return json;
    }

}
