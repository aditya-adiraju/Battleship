package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RadarMap extends Map {
    private static final String HIT_SQUARE = "X";
    private static final String MISSED_SQUARE = "O";

    RadarMap(int size) {
        super(size);
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: launches missile at given coordinates to opp board, true if it hits and false if miss
    boolean launchMissile(Player opponent, int x, int y) {
        return false;
    }
}
