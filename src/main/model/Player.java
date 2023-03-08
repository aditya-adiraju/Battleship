package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;

// CLASS-LEVEL COMMENT:
// This represents a player with an OceanMap and RadarMap
// they can launchAttacks to opponents or place a ship.
public class Player implements Writable {
    private final OceanMap oceanMap;
    private final RadarMap radarMap;
    private final String username;
    private final List<BattleShip> ships;

    // EFFECTS: creates a new Player with a radar map, ships, a username and an ocean map
    public Player(String username, int size) {
        this.oceanMap = new OceanMap(size);
        this.radarMap = new RadarMap(size);
        this.username = username;
        this.ships = new ArrayList<>();
    }

    // EFFECTS: Overloaded version of player constructor that assigns players all attributes immediately
    public Player(String username, OceanMap oceanMap, RadarMap radarMap, List<BattleShip> ships) {
        this.oceanMap = oceanMap;
        this.radarMap = radarMap;
        this.username = username;
        this.ships = ships;
    }

    public List<BattleShip> getShips() {
        return ships;
    }

    // EFFECTS: returns the ocean map as a formatted grid
    public String[][] getOceanBoard() {
        return oceanMap.getBoard();
    }

    // EFFECTS: returns the radar map as a formatted grid
    public String[][] getRadarBoard() {
        return radarMap.getBoard();
    }

    // EFFECTS: returns the maximum number of ships on the board.
    public int getMaximumShips() {
        return oceanMap.getMaxNumberOfShips();
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: returns true if a ship is present at a given point, and sinks ship.
    public boolean hitTarget(int x, int y) {
        if (oceanMap.isHit(x, y)) {
            removeShipCoordinate(x, y);
            return true;
        }
        return false;

    }

    // REQUIRES: 0 <= x < size, 0 <= y < size, A BattleShip in ships must hold coordinate
    // MODIFIES: this
    // EFFECTS: removes coordinate of battleship that has been hit, and sinks ship if all coordinates are hit
    private void removeShipCoordinate(int x, int y) {
        for (BattleShip s: ships) {
            if (s.contains(x, y)) {
                s.removeCoordinate(x, y);
            }
        }
        removeSunkenShips();
    }

    // MODIFIES: this
    // EFFECTS: removes ships that have been sunk with no live coordinates
    // RESOURCE USED:
    // https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#removeIf-java.util.function.Predicate-
    private void removeSunkenShips() {
        ships.removeIf(BattleShip::isEmpty);
    }


    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: places ship such that first coordinate is at given position
    public boolean placeShip(BattleShip bs, int x, int y) {
        if (oceanMap.placeShip(bs, x, y)) {
            ships.add(bs);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this, opp
    // EFFECTS: launches a missile at a given opponent's board
    public boolean launchAttack(Player opp, int x, int y) {
        return radarMap.launchMissile(opp, x, y);
    }

    // EFFECTS: returns the player's username
    public String getUsername() {
        return username;
    }

    // EFFECTS: checks whether any ships are remaining
    public boolean isLose() {
        return ships.isEmpty();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray shipsJson = new JSONArray();
        for (BattleShip b : ships) {
            shipsJson.put(b.toJson());
        }
        json.put("username", username);
        json.put("oceanMap", oceanMap.toJson());
        json.put("radarMap", radarMap.toJson());
        json.put("ships", shipsJson);

        return json;
    }
}