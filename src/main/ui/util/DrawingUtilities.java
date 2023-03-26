package ui.util;

import model.Map;
import model.OceanMap;
import model.RadarMap;
import ui.templates.MapButton;

import javax.swing.*;
import java.awt.*;

// CLASS-LEVEL COMMENT: class with util methods to draw elements on screen
public class DrawingUtilities {
    static ImageIcon greenSquare = new ImageIcon(DrawingUtilities.class.getResource("images/greenSquare.png"));
    static ImageIcon redSquare = new ImageIcon(DrawingUtilities.class.getResource("images/redSquare.png"));
    static ImageIcon yellowSquare = new ImageIcon(DrawingUtilities.class.getResource("images/yellowSquare.png"));
    static ImageIcon blueSquare = new ImageIcon(DrawingUtilities.class.getResource("images/blueSquare.png"));
    static ImageIcon greenCircle = new ImageIcon(DrawingUtilities.class.getResource("images/greenCircle.png"));
    static ImageIcon redCircle = new ImageIcon(DrawingUtilities.class.getResource("images/redCircle.png"));

    public static JPanel renderMap(Map map) {
        JPanel enclosingPanel = new JPanel();
        String[][] board = map.getBoard();
        int size = board.length;
        int gap = 10;
        enclosingPanel.setLayout(new GridLayout(size, size, gap, gap));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                MapButton btn = addButton(board, i, j);
                enclosingPanel.add(btn);
            }
        }
        return enclosingPanel;
    }

    private static MapButton addButton(String[][] board, int i, int j) {
        MapButton btn = new MapButton(j, i);
        btn.setFocusable(false);
        btn.addActionListener(e -> {
            System.out.println("x: " + btn.getXCoordinate() + " y:" + btn.getYCoordinate());
            btn.setEnabled(false);
        });
        if (board[j][i].equals(Map.EMPTY_SQUARE)) {
            btn.setIcon(greenSquare);
        } else if (board[j][i].equals(OceanMap.SHIP)) {
            btn.setIcon(blueSquare);
        } else if (board[j][i].equals(OceanMap.SUNKEN_SHIP)) {
            btn.setIcon(redSquare);
        } else if (board[j][i].equals(OceanMap.MISSED_ATTACK)) {
            btn.setIcon(yellowSquare);
        } else if (board[j][i].equals(RadarMap.HIT_MISSILE)) {
            btn.setIcon(redCircle);
        } else if (board[j][i].equals(RadarMap.MISSED_MISSILE)) {
            btn.setIcon(greenCircle);
        } else {
            btn.setText(j + " " + i);
        }
        btn.setOpaque(true);
        return btn;
    }
}
