package ui;

import model.OceanMap;
import model.RadarMap;
import ui.templates.GameFrame;
import ui.util.DrawingUtilities;

import java.awt.*;

public class GUI {
    public GUI() {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setLayout(new GridLayout(2, 1, 50, 50));
        gameFrame.setPreferredSize(new Dimension(400, 900));
        gameFrame.add(DrawingUtilities.renderMap(new RadarMap(10)));
        gameFrame.add(DrawingUtilities.renderMap(new OceanMap(10)));
        gameFrame.pack();
        gameFrame.setVisible(true);

    }
}
