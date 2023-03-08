package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// CLASS-LEVEL COMMENT:
// This represents the player's ocean map which holds their ships
// You can place ships and check whether a given coordinate hits a ship.
public class OceanMap extends Map implements Writable {
    public static final double PERCENTAGE_OF_BOARD = 0.05;
    private static final int[] codePoints1 = {0x1F7E6};
    private static final int[] codePoints2 = {0x1F7E5};
    private static final int[] codePoints3 = {0x1F7E8};

    public static final String SHIP = new String(codePoints1, 0, 1);
    public static final String SUNKEN_SHIP = new String(codePoints2, 0, 1);
    public static final String MISSED_ATTACK = new String(codePoints3, 0, 1);
    protected int numberOfShips;
    protected int maxNumberOfShips;

    // EFFECTS: creates an OceanMap with given size and decides on maximum number pf Ships
    public OceanMap(int size) {
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

    public void setNumberOfShips(int numberOfShips) {
        this.numberOfShips = numberOfShips;
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
        if (!board[y][x].equals(SUNKEN_SHIP)) {
            setCoordinate(MISSED_ATTACK, x, y);
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("numberOfShips", numberOfShips);
        return json;
    }
}
