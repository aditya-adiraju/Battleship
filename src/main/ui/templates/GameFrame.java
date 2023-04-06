package ui.templates;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// CLASS-LEVEL COMMENT: Frame that holds Game Content
public class GameFrame extends JFrame implements WindowListener {

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates a new Game Frame to hold game content in
    public GameFrame() {
        this.setTitle("BattleShip: The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 900));
        this.setPreferredSize(new Dimension(800, 900));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        EventLog log = EventLog.getInstance();
        for (Event event : log) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
