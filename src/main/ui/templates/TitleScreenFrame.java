package ui.templates;

import model.BattleShip;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import ui.Game;
import ui.util.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static ui.CLI.GAME_PATH;

// CLASS-LEVEL COMMENT: the title screen of the game
public class TitleScreenFrame extends JFrame implements ActionListener, WindowListener {
    JPanel buttonPanel;
    JPanel titlePanel;
    JPanel imagePanel;
    JLabel heading;
    JLabel subheading;

    JButton newGame;
    JButton loadGame;
    JButton showScores;
    JButton exitGame;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Creates a window with the title
    public TitleScreenFrame() {
        this.setTitle("BattleShip: The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        this.setPreferredSize(new Dimension(600, 600));
        this.getContentPane().setBackground(Color.darkGray);
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: displays the created window with buttons
    private void renderFrame() {
        renderTitlePanel();
        renderImagePanel();
        renderButtonPanel();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(titlePanel);
        this.add(imagePanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
        this.pack();
        this.setVisible(true);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds the formatted title to the title window
    private void renderTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        heading = new JLabel("BATTLESHIP");
        heading.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        heading.setFont(new Font("Papyrus", Font.BOLD, 80));
        heading.setForeground(Color.red);
        heading.setBackground(Color.darkGray);

        subheading = new JLabel("THE GAME");
        subheading.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        subheading.setFont(new Font("Papyrus", Font.PLAIN, 50));
        subheading.setForeground(Color.white);
        subheading.setBackground(Color.darkGray);

        titlePanel.setBackground(Color.darkGray);
        titlePanel.add(heading);
        titlePanel.add(subheading);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds an image to the window
    private void renderImagePanel() {
        imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(Icons.BattleShipIcon);
        label.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        imagePanel.add(label);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds all the buttons to the window
    private void renderButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.darkGray);

        newGame = new JButton("Start new game");
        loadGame = new JButton("Load saved game");
        showScores = new JButton("Show High Scores");
        exitGame = new JButton("Exit game");

        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        showScores.addActionListener(this);
        exitGame.addActionListener(this);

        buttonPanel.add(newGame);
        buttonPanel.add(loadGame);
        buttonPanel.add(showScores);
        buttonPanel.add(exitGame);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: executes correct method for each button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            this.dispose();
            new NewGameInitFrame();
        } else if (e.getSource() == loadGame) {
            this.dispose();
            JOptionPane.showMessageDialog(null, "Game loaded successfully",
                    "Game loaded!", JOptionPane.INFORMATION_MESSAGE);
            new TurnFrame(loadGame());
        } else if (e.getSource() == showScores) {
            this.dispose();
            new HighScoresFrame();
        } else if (e.getSource() == exitGame) {
            EventLog log = EventLog.getInstance();
            for (Event event : log) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
    }


    // EFFECTS: loads a saved game from game.json
    static Game loadGame() {
        JsonReader jsonReader = new JsonReader(GAME_PATH);
        try {
            return jsonReader.readGame();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot Find Save File",
                    "File not found", JOptionPane.ERROR_MESSAGE);
        }
        return null;
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
