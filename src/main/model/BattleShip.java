package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// CLASS-LEVEL COMMENT:
// This represents a battleship as list of coordinates in the x y plane.
// It can be rotated or translated to other points.
// Coordinates can be removed as well to indicate a sunk ship
public class BattleShip implements Writable {
    int size;
    private List<Point> coordinates = new ArrayList<>();

    // EFFECTS: creates a new battleship with given number of coordinates on cartesian plane
    public BattleShip(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.coordinates.add(new Point(i, 0));
        }
    }

    public int getSize() {
        return size;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    // EFFECTS: checks whether given coordinate exists on battleship
    public boolean contains(int x, int y) {
        return coordinates.contains(new Point(x, y));
    }

    // MODIFIES: this
    // EFFECTS: rotates all coordinates on ship 90 counter-clockwise
    public void rotate() {
        int newX;
        int newY;
        int temp;
        Point midpoint = coordinates.get(size / 2);
        int midX = midpoint.x;
        int midY = midpoint.y;
        for (Point coordinate: coordinates) {
            newX = coordinate.x;
            newY = coordinate.y;

            newX -= midX;
            newY -= midY;

            temp = newX;
            newX = -newY;
            newY =  temp;

            newX += midX;
            newY += midY;

            coordinate.setLocation(newX, newY);
        }
    }

    // REQUIRES: size of coordinates > 0
    // MODIFIES: this
    // EFFECTS: translates coordinates to be w.r.t. given coordinate
    public void translate(int x, int y) {
        Point baseCoordinate = getBaseCoordinate();
        int translationFactorX = x - baseCoordinate.x;
        int translationFactorY = y - baseCoordinate.y;

        for (Point coordinate: coordinates) {
            coordinate.translate(translationFactorX, translationFactorY);
        }
    }

    // EFFECTS: returns the start coordinate by finding
    private Point getBaseCoordinate() {
        Point result = coordinates.get(0);
        int minimumSum = result.x + result.y;
        int coordinateSum;
        for (Point coordinate : coordinates) {
            coordinateSum = coordinate.x + coordinate.y;
            result = (coordinateSum < minimumSum) ? coordinate : result;
        }
        return result;
    }

    // REQUIRES: Point(x, y) must be in coordinates
    // MODIFIES: this
    // EFFECTS: remove given coordinate
    public void removeCoordinate(int x, int y) {
        coordinates.remove(new Point(x, y));
    }

    //EFFECTS: checks whether there are any unstruck coordinates in ship
    public boolean isEmpty() {
        return coordinates.size() == 0;
    }


    // EFFECTS: returns a grid representing the battleship shape
    public String[][] getShipBoard() {
        String[][] grid = new String[size][size];
        for (String[] row: grid) {
            Arrays.fill(row, Map.EMPTY_SQUARE);
        }
        translate(0, 0);
        for (Point coordinate: getCoordinates()) {
            grid[coordinate.y][coordinate.x] = OceanMap.SHIP;
        }
        grid[0][0] = OceanMap.SUNKEN_SHIP;
        return grid;
    }

    @Override
    public JSONObject toJson() {
        JSONObject point;
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        json.put("size", size);
        for (Point c : coordinates) {
            point = new JSONObject();
            point.put("x", c.x);
            point.put("y", c.y);
        }
        json.put("coordinates", jsonArray);
        return json;
    }
}
