package ui.templates;

import model.Event;
import model.EventLog;
import model.Score;
import ui.CLI;
import ui.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

// CLASS-LEVEL COMMENT: Frame that displays high scores
public class HighScoresFrame extends JFrame implements ActionListener, WindowListener {
    JPanel scoresPanel;
    JPanel buttonPanel;
    JButton newGame;
    JButton exit;
    java.util.List<Score> scoreList;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a High Scores window to display scores
    public HighScoresFrame() {
        this.scoreList = CLI.loadScores();
        this.setTitle("High Scores");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 400));
        this.setPreferredSize(new Dimension(300, 400));
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds scores and buttons to window and displays it
    private void renderFrame() {
        JPanel enclosingPanel = new JPanel();
        renderButtonPanel();
        renderScoresPanel();
        enclosingPanel.setBackground(Color.darkGray);
        enclosingPanel.setLayout(new BoxLayout(enclosingPanel, BoxLayout.Y_AXIS));
        enclosingPanel.add(Box.createVerticalGlue());
        enclosingPanel.add(scoresPanel);
        enclosingPanel.add(buttonPanel);
        enclosingPanel.add(Box.createVerticalGlue());
        this.add(enclosingPanel);
        this.pack();
        this.setVisible(true);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds each Score to a Panel showing the scores
    public void renderScoresPanel() {
        scoresPanel = new JPanel();
        scoresPanel.setBackground(Color.darkGray);
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("HIGH SCORES");
        title.setFont(new Font("Papyrus", Font.BOLD, 30));
        title.setForeground(Color.white);

        scoresPanel.add(Box.createVerticalGlue());
        title.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        scoresPanel.add(title);

        JLabel score;
        for (Score s : scoreList) {
            score = new JLabel(s.getName() + ": " + s.getPoints());
            score.setForeground(Color.white);
            score.setFont(new Font("Papyrus", Font.BOLD, 20));
            score.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            scoresPanel.add(score);
        }
        scoresPanel.add(Box.createVerticalGlue());
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds Buttons to a panel with buttons
    private void renderButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.darkGray);

        exit = new JButton("Exit");
        newGame = new JButton("New Game");
        exit.addActionListener(this);
        newGame.addActionListener(this);
        buttonPanel.add(exit);
        buttonPanel.add(newGame);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: executes correct methods based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            EventLog log = EventLog.getInstance();
            for (Event event : log) {
                System.out.println(event.toString());
            }
            System.exit(0);
        } else if (e.getSource() == newGame) {
            this.dispose();
            new TitleScreenFrame();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowOpened(WindowEvent e) {

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: prints event log
    @Override
    public void windowClosing(WindowEvent e) {
        EventLog log = EventLog.getInstance();
        for (Event event : log) {
            System.out.println(event.toString());
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: does nothing
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
