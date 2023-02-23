package model;

import java.awt.*;

public class OceanMap extends Map {
    public static final double PERCENTAGE_OF_BOARD = 0.05;
    private static int[] codePoints1 = {0x1F7E6};
    private static int[] codePoints2 = {0x1F7E5};

    public static final String SHIP = new String(codePoints1, 0, 1);
    public static final String SUNKEN_SHIP = new String(codePoints2, 0, 1);
    protected int numberOfShips;
    protected int maxNumberOfShips;

    OceanMap(int size) {
        super(size);
        this.numberOfShips = 0;
        this.maxNumberOfShips = (int) (PERCENTAGE_OF_BOARD * size * size);
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public int getMaxNumberOfShips() {
        return maxNumberOfShips;
    }

    // REQUIRES: battleship to be placeable in the board, numberOfShips < maxNumberOfShips
    // MODIFIES: this
    // EFFECTS: places ship on board and modifies required squares on board
    void placeShip(BattleShip ship, int x, int y) {
        ship.translate(x, y);
        for (Point coordinate: ship.getCoordinates()) {
            setCoordinate(SHIP, coordinate.x, coordinate.y);
        }
        numberOfShips++;
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // EFFECTS: returns true if a ship is present at a given coordinate
    boolean isHit(int x, int y) {
        if (board[y][x].equals(SHIP)) {
            setCoordinate(SUNKEN_SHIP, x, y);
            return true;
        }
        return false;
    }
}
