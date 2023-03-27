package ui.util.mappanels;

import model.Map;
import model.OceanMap;
import ui.templates.MapButton;
import ui.templates.SelectionFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import static ui.util.Icons.*;

public class ShipMapPanel {

    SelectionFrame parentFrame;
    JPanel shipPanel;

    public ShipMapPanel(SelectionFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public SelectionFrame getParentFrame() {
        return parentFrame;
    }

    public void setParentFrame(SelectionFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

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

    MapButton addButton(String[][] board, int i, int j) {
        MapButton btn = new MapButton(j, i);
        btn.setFocusable(false);
        parseButton(board, i, j, btn);
        btn.setOpaque(true);
        return btn;
    }

    void setDisabledButton(MapButton btn, ImageIcon imageIcon) {
        btn.setDisabledIcon(imageIcon);
        btn.setIcon(imageIcon);
        btn.setEnabled(false);
    }
}
