package model;

public class Player {
    private OceanMap oceanMap;
    private RadarMap radarMap;
    private String username;

    public Player(String username, int size) {
        this.oceanMap = new OceanMap(size);
        this.radarMap = new RadarMap(size);
        this.username = username;
    }

    public String[][] getOceanMap() {
        return oceanMap.getBoard();
    }

    public String[][] getRadarMap() {
        return radarMap.getBoard();
    }

    // REQUIRES: 0 <= x < size, 0 <= y < size
    // MODIFIES: this
    // EFFECTS: returns true if a ship is present at a given, and sinks ship.
    public boolean hitTarget(int x, int y) {
        return false;
    }

    // REQUIRES: battleship to be placeable in the board
    // MODIFIES: this
    // EFFECTS: places ship such that first coordinate is at given position
    public void placeShip(BattleShip bs, int x, int y) {

    }
    
    // EFFECTS: returns the player's username
    public String getUsername() {
        return username;
    }
}