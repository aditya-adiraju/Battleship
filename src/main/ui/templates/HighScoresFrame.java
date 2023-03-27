package ui.templates;

import model.Score;
import ui.CLI;
import ui.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HighScoresFrame extends JFrame implements ActionListener {
    JPanel scoresPanel;
    JPanel buttonPanel;
    JButton newGame;
    JButton exit;
    java.util.List<Score> scoreList;

    public HighScoresFrame() {
        this.scoreList = CLI.loadScores();
        this.setTitle("High Scores");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 400));
        this.setPreferredSize(new Dimension(300, 400));
        renderFrame();
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == newGame) {
            this.dispose();
            new TitleScreenFrame();
        }
    }

}
