package ui.templates;

import javax.swing.*;
import java.awt.*;

// CLASS-LEVEL COMMENT: Frame that holds Game Content
public class GameFrame extends JFrame {

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates a new Game Frame to hold game content in
    public GameFrame() {
        this.setTitle("BattleShip: The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 900));
        this.setPreferredSize(new Dimension(800, 900));
        this.getContentPane().setBackground(Color.DARK_GRAY);
    }
}
