package ui.util.mappanels;

import model.Map;
import model.OceanMap;
import ui.templates.MapButton;
import ui.templates.SelectionFrame;
import ui.util.Icons;

import javax.swing.*;
import java.awt.*;

public class ShipMapPanel {
    static ImageIcon greenSquare = new ImageIcon(Icons.class.getResource("images/greenSquare.png"));
    static ImageIcon graySquare = new ImageIcon(Icons.class.getResource("images/graySquare.png"));
    static ImageIcon cyanSquare = new ImageIcon(Icons.class.getResource("images/cyanSquare.png"));
    static ImageIcon redSquare = new ImageIcon(Icons.class.getResource("images/redSquare.png"));
    static ImageIcon yellowSquare = new ImageIcon(Icons.class.getResource("images/yellowSquare.png"));
    static ImageIcon blueSquare = new ImageIcon(Icons.class.getResource("images/blueSquare.png"));
    static ImageIcon greenCircle = new ImageIcon(Icons.class.getResource("images/greenCircle.png"));
    static ImageIcon redCircle = new ImageIcon(Icons.class.getResource("images/redCircle.png"));
    static ImageIcon pinkCircle = new ImageIcon(Icons.class.getResource("images/pinkCircle.png"));

    SelectionFrame parentFrame;
    JPanel enclosingPanel;

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
        enclosingPanel = new JPanel();
        int size = board.length;
        int gap = 5;
        enclosingPanel.setLayout(new GridLayout(size, size, gap, gap));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                MapButton btn = addButton(board, i, j);
                enclosingPanel.add(btn);
            }
        }
        return enclosingPanel;
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
