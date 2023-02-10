package model;

public class OceanMap extends Map {
    public static final double PERCENTAGE_OF_BOARD = 0.05;
    private static final String EMPTY_SQUARE = " ";
    private static final String INJURED_SHIP = " ";
    private static final String SUNKEN_SHIP = " ";
    int numberOfShips;
    int maximumNumberOfShips;

    OceanMap(int size) {
        super(size);
        this.numberOfShips = 0;
        this.maximumNumberOfShips = (int) (PERCENTAGE_OF_BOARD * size * size);
    }

    // REQUIRES: size > x, y >= 0
    // MODIFIES: this
    // EFFECTS: places ship on board and modifies required squares on board
    void placeShip(BattleShip ship, int x, int y) {

    }

    // REQUIRES: size > x, y >= 0
    // EFFECTS: returns true if a ship is present at a given coordinate
    boolean isHit(int x, int y) {
        return !this.board[x][y].equals(EMPTY_SQUARE);
    }
}
