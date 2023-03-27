package ui.util.textpanels;

import model.Player;
import ui.templates.GameFrame;
import ui.util.Icons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlayerInfoPanel {
    private String name;
    ImageIcon icon;
    JLabel nameLabel;
    GameFrame gameFrame;
    JTextArea description;


    public PlayerInfoPanel(Player player, Boolean isGame) {
        this.name = player.getUsername();
        this.icon = getPlayerIcon();
        this.nameLabel = new JLabel(this.name);
        if (isGame) {
            this.description = getTurnDescriptionLabel();
        } else {
            this.description = getShipDescriptionLabel();
        }

        renderPlayerPanel();
    }

    private JTextArea getShipDescriptionLabel() {
        JTextArea shipDesc = new JTextArea();
        shipDesc.setText(String.format("Click on a coordinate on the top map to place the ship seen on the bottom. "
                + "You are free to rotate the ship"));
        return shipDesc;
    }

    private JTextArea getTurnDescriptionLabel() {
        JTextArea turnDesc = new JTextArea();
        turnDesc.setText(String.format("Guess where your opponent hid their ships. "
                + "Click on a coordinate on your green radar map to launch an attack"));
        return turnDesc;
    }

    public JPanel renderPlayerPanel() {
        JPanel enclosingPanel = new JPanel();
        JLabel iconLabel = new JLabel();
        enclosingPanel.setBackground(Color.black);
        enclosingPanel.setLayout(new BoxLayout(enclosingPanel, BoxLayout.Y_AXIS));
        iconLabel.setIcon(icon);
        iconLabel.setAlignmentX(enclosingPanel.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Papyrus", Font.BOLD, 40));
        nameLabel.setForeground(Color.white);
        nameLabel.setAlignmentX(enclosingPanel.CENTER_ALIGNMENT);
        description.setLineWrap(true);
        enclosingPanel.add(Box.createVerticalGlue());
        enclosingPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        enclosingPanel.add(iconLabel);
        enclosingPanel.add(nameLabel);
        enclosingPanel.add(description);
        enclosingPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        enclosingPanel.add(Box.createVerticalGlue());
        enclosingPanel.setBorder(BorderFactory.createLineBorder(Color.black, 20));
        return enclosingPanel;
    }

    private ImageIcon getPlayerIcon() {
        return Icons.userIcon;
    }
}
