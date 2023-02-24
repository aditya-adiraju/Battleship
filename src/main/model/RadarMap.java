package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RadarMap extends Map {
    private static int[] codePoints1 = {0x1F7E0};
    private static int[] codePoints2 = {0x1F7E2};

    public static final String HIT_SQUARE = new String(codePoints1, 0, 1);
    public static final String MISSED_SQUARE = new String(codePoints2, 0, 1);

    RadarMap(int size) {
        super(size);
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: launches missile at given coordinates to opp board, true if it hits and false if miss
    boolean launchMissile(Player opp, int x, int y) {
        if (opp.hitTarget(x, y)) {
            this.setCoordinate(HIT_SQUARE, x, y);
            return true;
        } else {
            if (board[y][x].equals(EMPTY_SQUARE)) {
                this.setCoordinate(MISSED_SQUARE, x, y);
            }
            return false;
        }
    }
}
