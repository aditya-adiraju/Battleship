package model;

public class OceanMap extends Map {
    public static final double PERCENTAGE_OF_BOARD = 0.05;
    private static final String SHIP = "□";
    private static final String INJURED_SHIP = "I";
    private static final String SUNKEN_SHIP = "X";
    int numberOfShips;
    int maxNumberOfShips;

    OceanMap(int size) {
        super(size);
        this.numberOfShips = 0;
        this.maxNumberOfShips = (int) (PERCENTAGE_OF_BOARD * size * size);
    }

    public int getMaxNumberOfShips() {
        return maxNumberOfShips;
    }

    // REQUIRES: battleship to be placeable in the board, numberOfShips < maxNumberOfShips
    // MODIFIES: this
    // EFFECTS: places ship on board and modifies required squares on board
    void placeShip(BattleShip ship, int x, int y) {

    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // EFFECTS: returns true if a ship is present at a given coordinate
    boolean isHit(int x, int y) {
        return !this.board[x][y].equals(EMPTY_SQUARE);
    }

    // EFFECTS: checks whether there are any ships left on the board
    boolean isOver() {
        return false;
    }
}
