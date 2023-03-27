package ui.templates;

import javax.swing.*;
import java.awt.*;

// CLASS-LEVEL COMMENT: Button that holds x, y coordinates of itself
public class MapButton extends JButton {
    int[] coordinates;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a new button that holds an x,y coordinate
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: gets the x coordinate of the button
    public int getXCoordinate() {
        return coordinates[0];
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: gets the y coordinate of the button
    public int getYCoordinate() {
        return coordinates[1];
    }
}
