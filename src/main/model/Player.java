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

    // REQUIRES: size > x, y >= 0
    // MODIFIES: this
    // EFFECTS: returns true if a ship is present at a given, and sinks ship.
    public boolean hitTarget(int x, int y) {
        return false;
    }

    // EFFECTS: returns the player's username
    public String getUsername() {
        return username;
    }
}