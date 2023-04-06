package ui.templates;

import model.Event;
import model.EventLog;
import ui.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// CLASS-LEVEL COMMENT: A form frame that takes in the parameters of the game
public class NewGameInitFrame extends JFrame implements ActionListener, WindowListener {
    JPanel formPanel;
    JPanel buttonPanel;
    JLabel p1Label;
    JTextField p1Name;
    JLabel p2Label;
    JTextField p2Name;
    JLabel sizeLabel;
    SpinnerModel sm = new SpinnerNumberModel(5, 5, 26, 1);
    JSpinner size;
    JButton submit;
    JButton exit;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Creates a window for user to input game parameters
    public NewGameInitFrame() {
        this.setTitle("Start new Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setPreferredSize(new Dimension(400, 400));
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds components and displays parts on the window
    private void renderFrame() {
        renderFormPanel();
        renderButtonPanel();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(formPanel);
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());
        this.pack();
        this.setVisible(true);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds text fields and inputs to a panel with the form
    private void renderFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));

        p1Label = new JLabel("Player 1 Name: ");
        p1Name = new JTextField("P1");
        p2Label = new JLabel("Player 2 Name: ");
        p2Name = new JTextField("P2");
        sizeLabel = new JLabel("Size: ");
        size = new JSpinner(sm);
        size.setEditor(new JSpinner.DefaultEditor(size));

        formPanel.add(p1Label);
        formPanel.add(p1Name);
        formPanel.add(p2Label);
        formPanel.add(p2Name);
        formPanel.add(sizeLabel);
        formPanel.add(size);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds buttons to a panel with the buttons
    private void renderButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        submit = new JButton("Submit");
        exit = new JButton("Exit");

        submit.addActionListener(this);
        exit.addActionListener(this);

        buttonPanel.add(submit);
        buttonPanel.add(exit);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: executes correct methods based on button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            this.dispose();
            new SelectionFrame(new Game(p1Name.getText(), p2Name.getText(), (int) size.getValue()));
        } else if (e.getSource() == exit) {
            EventLog log = EventLog.getInstance();
            for (Event event : log) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
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
