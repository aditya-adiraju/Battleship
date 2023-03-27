package ui.util.mappanels;

import model.Map;
import model.OceanMap;
import model.RadarMap;
import ui.Game;
import ui.templates.MapButton;
import ui.templates.TurnFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static ui.util.Icons.*;

public class TurnMapPanel {

    TurnFrame parentFrame;
    JPanel enclosingPanel;

    public TurnMapPanel(TurnFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public TurnFrame getParentFrame() {
        return parentFrame;
    }

    public void setParentFrame(TurnFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public JPanel renderMap(String[][] board, Boolean disabled) {
        enclosingPanel = new JPanel();
        int size = board.length;
        int gap = 10;
        enclosingPanel.setLayout(new GridLayout(size, size, gap, gap));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                MapButton btn = addButton(board, i, j, disabled);
                enclosingPanel.add(btn);
            }
        }
        return enclosingPanel;
    }

    void parseButton(String[][] board, int i, int j, Boolean disabled, MapButton btn) {
        String target = board[i][j];
        if (target.equals(Map.EMPTY_SQUARE)) {
            if (disabled) {
                setDisabledButton(btn, cyanSquare);
            } else {
                btn.setIcon(greenSquare);
            }
        } else if (target.equals(OceanMap.SHIP)) {
            setDisabledButton(btn, blueSquare);
        } else if (target.equals(OceanMap.SUNKEN_SHIP)) {
            setDisabledButton(btn, redSquare);
        } else if (target.equals(OceanMap.MISSED_ATTACK)) {
            setDisabledButton(btn, yellowSquare);
        } else if (target.equals(RadarMap.HIT_MISSILE)) {
            setDisabledButton(btn, redCircle);
        } else if (target.equals(RadarMap.MISSED_MISSILE)) {
            setDisabledButton(btn, greenCircle);
        } else {
            btn.setText(j + " " + i);
        }
    }

    void setDisabledButton(MapButton btn, ImageIcon imageIcon) {
        btn.setDisabledIcon(imageIcon);
        btn.setIcon(imageIcon);
        btn.setEnabled(false);
    }


    MapButton addButton(String[][] board, int i, int j, Boolean disabled) {
        MapButton btn = new MapButton(j, i);
        btn.setFocusable(false);
        btn.addActionListener(getActionListener(btn));
        parseButton(board, i, j, disabled, btn);
        btn.setOpaque(true);
        return btn;
    }

    ActionListener getActionListener(MapButton btn) {
        return e -> {
            Game game = parentFrame.getGame();
            Boolean successful;
            int x = btn.getXCoordinate();
            int y = btn.getYCoordinate();
            if (game.getCurrentPlayer() == 0) {
                successful = game.getPlayer1().launchAttack(game.getPlayer2(), x, y);
            } else {
                successful = game.getPlayer2().launchAttack(game.getPlayer1(), x, y);
            }
            if (successful) {
                setDisabledButton(btn, redCircle);
                JOptionPane.showMessageDialog(null, "You have successfully hit a ship",
                        "It's a Strike", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.checkEndGame();
            } else {
                setDisabledButton(btn, greenCircle);
                JOptionPane.showMessageDialog(null, "You missed!",
                        "It's a miss", JOptionPane.INFORMATION_MESSAGE);
                parentFrame.endTurn();
            }
        };
    }
}
