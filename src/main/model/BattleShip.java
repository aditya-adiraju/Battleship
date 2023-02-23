package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BattleShip {
    int size;
    private List<Point> coordinates = new ArrayList<>();

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
        Point baseCoordinate = coordinates.get(0);
        int translationFactorX = x - baseCoordinate.x;
        int translationFactorY = y - baseCoordinate.y;

        for (Point coordinate: coordinates) {
            coordinate.translate(translationFactorX, translationFactorY);
        }
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
}
