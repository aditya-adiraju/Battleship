package ui.templates;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("BattleShip: The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 900));
        this.setPreferredSize(new Dimension(800, 900));
        this.getContentPane().setBackground(Color.DARK_GRAY);
    }
}
