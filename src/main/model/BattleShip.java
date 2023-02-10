package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class BattleShip {
    int size;
    private List<Point> coordinates = new ArrayList<Point>();

    public BattleShip(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.coordinates.add(new Point(i, 0));
        }
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    // MODIFIES: this
    // EFFECTS: rotates all coordinates on ship 90 counter-clockwise
    public void rotate() {
        int newX;
        int newY;
        int temp;
        Point midpoint = coordinates.get(size / 2);
        for (Point coordinate: coordinates) {
            newX = (int) coordinate.getX();
            newY = (int) coordinate.getY();

            newX += midpoint.x;
            newY += midpoint.y;

            temp = newX;
            newX = -newY;
            newY =  temp;

            newX -= midpoint.x;
            newY -= midpoint.y;

            coordinate.setLocation(newX, newY);
        }
    }
}
