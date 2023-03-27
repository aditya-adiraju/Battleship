package ui.util.textpanels;

import model.Player;
import ui.templates.GameFrame;
import ui.util.Icons;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// CLASS-LEVEL COMMENT: Panel with the player's information displayed
public class PlayerInfoPanel {
    private String name;
    ImageIcon icon;
    JLabel nameLabel;
    GameFrame gameFrame;
    JTextArea description;

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a panel containing the user's information
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns a text area with the appropriate description for adding ships
    private JTextArea getShipDescriptionLabel() {
        JTextArea shipDesc = new JTextArea();
        shipDesc.setText("Click on a coordinate on the top map to place the ship seen on the bottom. "
                + "You are free to rotate the ship");
        return shipDesc;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns a text area with the appropriate description for a battle phase of the game
    private JTextArea getTurnDescriptionLabel() {
        JTextArea turnDesc = new JTextArea();
        turnDesc.setText("Guess where your opponent hid their ships. "
                + "Click on a coordinate on your green radar map to launch an attack");
        return turnDesc;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: merges all components of the player info panel
    public JPanel renderPlayerPanel() {
        JPanel enclosingPanel = new JPanel();
        JLabel iconLabel = new JLabel();
        enclosingPanel.setBackground(Color.black);
        enclosingPanel.setLayout(new BoxLayout(enclosingPanel, BoxLayout.Y_AXIS));
        iconLabel.setIcon(icon);
        iconLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Papyrus", Font.BOLD, 40));
        nameLabel.setForeground(Color.white);
        nameLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the user icon
    private ImageIcon getPlayerIcon() {
        return Icons.userIcon;
    }
}
