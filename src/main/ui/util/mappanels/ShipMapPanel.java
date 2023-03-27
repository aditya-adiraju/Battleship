package ui.util.mappanels;

import model.Map;
import model.OceanMap;
import ui.templates.MapButton;
import ui.templates.SelectionFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import static ui.util.Icons.*;

// CLASS-LEVEL COMMENT: Panel that displays a ship to be placed during the selection phase
public class ShipMapPanel {

    SelectionFrame parentFrame;
    JPanel shipPanel;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a panel that holds the ship to be placed on a player's map
    public ShipMapPanel(SelectionFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public SelectionFrame getParentFrame() {
        return parentFrame;
    }

    public void setParentFrame(SelectionFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds all buttons to the panel
    public JPanel renderMap(String[][] board) {
        shipPanel = new JPanel();
        int size = board.length;
        int gap = 5;
        shipPanel.setLayout(new GridLayout(size, size, gap, gap));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                MapButton btn = addButton(board, i, j);
                shipPanel.add(btn);
            }
        }
        return shipPanel;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: evaluates how to set icons for each button
    void parseButton(String[][] board, int i, int j, MapButton btn) {
        String target = board[i][j];
        if (target.equals(Map.EMPTY_SQUARE)) {
            setDisabledButton(btn, cyanSquare);
        } else if (target.equals(OceanMap.SHIP)) {
            setDisabledButton(btn, blueSquare);
        } else if (target.equals(OceanMap.SUNKEN_SHIP)) {
            setDisabledButton(btn, redSquare);
        } else {
            btn.setText(j + " " + i);
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a button with x and y information encoded in it
    MapButton addButton(String[][] board, int i, int j) {
        MapButton btn = new MapButton(j, i);
        btn.setFocusable(false);
        parseButton(board, i, j, btn);
        btn.setOpaque(true);
        return btn;
    }

    // EFFECTS: sets a button with an icon that is disabled
    void setDisabledButton(MapButton btn, ImageIcon imageIcon) {
        btn.setDisabledIcon(imageIcon);
        btn.setIcon(imageIcon);
        btn.setEnabled(false);
    }
}
