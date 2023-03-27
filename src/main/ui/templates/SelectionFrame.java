package ui.templates;

import model.BattleShip;
import model.Player;
import ui.Game;
import ui.util.mappanels.SelectionMapPanel;
import ui.util.mappanels.ShipMapPanel;
import ui.util.textpanels.PlayerInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// CLASS-LEVEL COMMENT: Frame that displays when the players are assigning ships to their maps
public class SelectionFrame implements ActionListener {
    Game game;
    JButton rotateButton;
    SelectionMapPanel mapPanel;
    ShipMapPanel shipPanel;
    GameFrame gameFrame;
    int index = 0;
    BattleShip currentShip = new BattleShip(Game.shipSizes[index % Game.shipSizes.length]);
    BattleShip displayShip = new BattleShip(Game.shipSizes[index % Game.shipSizes.length]);


    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Creates a window for the player to add ships to their board
    public SelectionFrame(Game game) {
        this.game = game;
        this.index++;
        this.mapPanel = new SelectionMapPanel(this);
        this.mapPanel.setCurrentShip(currentShip);
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: displays the final window with all the visual components added to it
    private void renderFrame() {
        JPanel oceanDisplay = renderOceanDisplay();
        gameFrame = new GameFrame();
        gameFrame.setLayout(new GridLayout(1, 2));
        PlayerInfoPanel pip = new PlayerInfoPanel(getActualPlayer(), false);
        gameFrame.add(oceanDisplay);
        gameFrame.add(pip.renderPlayerPanel());
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: produces the Player currently playing the game
    private Player getActualPlayer() {
        if (game.getCurrentPlayer() == 0) {
            return game.getPlayer1();
        } else {
            return game.getPlayer2();
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates a panel with a small ship that is to be added to player's map
    private JPanel renderShipDisplay() {
        JPanel enclosingPanel = new JPanel();
        JPanel rotatePanel = createRotatePanel();
        int refactorSize = 2;
        enclosingPanel.setLayout(new GridLayout(refactorSize, refactorSize));
        shipPanel = new ShipMapPanel(this);

        enclosingPanel.add(shipPanel.renderMap(displayShip.getShipBoard()));
        enclosingPanel.add(rotatePanel);
        enclosingPanel.add(new JPanel());
        enclosingPanel.add(new JPanel());
        return enclosingPanel;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Creates a space to hold the rotate button
    private JPanel createRotatePanel() {
        JPanel rotatePanel = new JPanel();
        rotatePanel.setLayout(new BoxLayout(rotatePanel, BoxLayout.Y_AXIS));
        rotateButton = new JButton("Rotate");
        rotateButton.addActionListener(this);
        rotatePanel.add(Box.createVerticalGlue());
        rotatePanel.add(rotateButton);
        rotateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rotatePanel.add(Box.createVerticalGlue());
        return rotatePanel;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Creates a panel that displays the user's ocean map
    private JPanel renderOceanDisplay() {
        Player player;
        JPanel enclosingPanel = new JPanel();
        enclosingPanel.setLayout(new GridLayout(2, 1, 50, 50));
        if (game.getCurrentPlayer() == 0) {
            player = game.getPlayer1();
        } else {
            player = game.getPlayer2();
        }

        enclosingPanel.add(mapPanel.renderMap(player.getOceanBoard(), false));

        enclosingPanel.add(renderShipDisplay());
        return enclosingPanel;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: moves on to the next ship, and advances game state if needed
    public void nextShip() {
        int maxShips = game.getPlayer1().getMaximumShips();
        int p1CurrentShips = game.getPlayer1().getShips().size();
        int p2CurrentShips = game.getPlayer2().getShips().size();
        this.gameFrame.dispose();
        if (game.getCurrentPlayer() == 0 && maxShips <= p1CurrentShips) {
            passPlayer();
            game.changeCurrentPlayer();
            this.index = 0;
            setShips();
            renderFrame();
        } else if (game.getCurrentPlayer() == 1 && maxShips <= p2CurrentShips) {
            passPlayer();
            this.gameFrame.dispose();
            game.changeCurrentPlayer();
            new TurnFrame(game);
        } else {
            setShips();
            renderFrame();
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: reassigns ships with the current index
    private void setShips() {
        this.currentShip = new BattleShip(Game.shipSizes[index % Game.shipSizes.length]);
        this.displayShip = new BattleShip(Game.shipSizes[index % Game.shipSizes.length]);
        this.index++;
        this.mapPanel.setCurrentShip(currentShip);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: Creates a sequence to prevent cheating when passing a game
    private void passPlayer() {
        this.gameFrame.dispose();
        renderFrame();
        JOptionPane.showMessageDialog(null, "Press OK and Pass device to other player",
                "Player pass", JOptionPane.INFORMATION_MESSAGE);
        this.gameFrame.dispose();
        JOptionPane.showMessageDialog(null, "Press OK to continue",
                "Player pass", JOptionPane.INFORMATION_MESSAGE);
    }


    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: executes correct methods for the buttons pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rotateButton) {
            currentShip.rotate();
            displayShip.rotate();
            this.gameFrame.dispose();
            renderFrame();
        }
    }
}
