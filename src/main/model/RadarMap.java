package model;

// CLASS-LEVEL COMMENT:
// This represents a radar map
// You can launch a missile at an opponent's oceanMap
public class RadarMap extends Map {
    private static final int[] codePoints1 = {0x1F7E0};
    private static final int[] codePoints2 = {0x1F7E2};

    public static final String HIT_MISSILE = new String(codePoints1, 0, 1);
    public static final String MISSED_MISSILE = new String(codePoints2, 0, 1);

    public RadarMap(int size) {
        super(size);
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: launches missile at given coordinates to opp board, true if it hits and false if miss
    boolean launchMissile(Player opp, int x, int y) {
        if (opp.hitTarget(x, y)) {
            this.setCoordinate(HIT_MISSILE, x, y);
            return true;
        } else {
            if (board[y][x].equals(EMPTY_SQUARE)) {
                this.setCoordinate(MISSED_MISSILE, x, y);
            }
            return false;
        }
    }
}
