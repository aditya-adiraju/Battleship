package ui.templates;

import javax.swing.*;
import java.awt.*;

public class MapButton extends JButton {
    int[] coordinates;

    public MapButton(int x, int y) {
        super();
        this.setCoordinates(x, y);
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new int[]{x, y};
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getXCoordinate() {
        return coordinates[0];
    }

    public int getYCoordinate() {
        return coordinates[1];
    }
}
