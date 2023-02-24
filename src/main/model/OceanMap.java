package model;

import java.awt.*;

public class OceanMap extends Map {
    public static final double PERCENTAGE_OF_BOARD = 0.05;
    private static final int[] codePoints1 = {0x1F7E6};
    private static final int[] codePoints2 = {0x1F7E5};

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

    // REQUIRES: 0 <= x < size, 0 <= y < size, numberOfShips < maxNumberOfShips
    // MODIFIES: this
    // EFFECTS: places ship on board and modifies required squares on board
    boolean placeShip(BattleShip ship, int x, int y) {
        ship.translate(x, y);
        if (validPosition(ship)) {
            for (Point coordinate: ship.getCoordinates()) {
                setCoordinate(SHIP, coordinate.x, coordinate.y);
            }
            numberOfShips++;
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if a ship can be placed on map at present location
    private boolean validPosition(BattleShip ship) {
        try {
            for (Point coordinate : ship.getCoordinates()) {
                if (!board[coordinate.y][coordinate.x].equals(EMPTY_SQUARE)) {
                    return false;
                }
            }
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
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