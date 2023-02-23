package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private OceanMap oceanMap;
    private RadarMap radarMap;
    private String username;
    private List<BattleShip> ships;

    public Player(String username, int size) {
        this.oceanMap = new OceanMap(size);
        this.radarMap = new RadarMap(size);
        this.username = username;
        this.ships = new ArrayList<>();
    }

    public List<BattleShip> getShips() {
        return ships;
    }

    public String[][] getOceanMap() {
        return oceanMap.getBoard();
    }

    public String[][] getRadarMap() {
        return radarMap.getBoard();
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
        ships.removeIf(s -> s.isEmpty());
    }


    // REQUIRES: battleship to be placeable in the board
    // MODIFIES: this
    // EFFECTS: places ship such that first coordinate is at given position
    public void placeShip(BattleShip bs, int x, int y) {
        oceanMap.placeShip(bs, x, y);
        ships.add(bs);
    }

    // EFFECTS: returns the player's username
    public String getUsername() {
        return username;
    }
}