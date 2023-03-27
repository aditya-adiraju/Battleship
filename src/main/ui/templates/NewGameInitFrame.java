package ui.templates;

import ui.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameInitFrame extends JFrame implements ActionListener {
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

    public NewGameInitFrame() {
        this.setTitle("Start new Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setPreferredSize(new Dimension(400, 400));
        renderFrame();
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            this.dispose();
            new SelectionFrame(new Game(p1Name.getText().strip(), p2Name.getText().strip(), (int) size.getValue()));
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
