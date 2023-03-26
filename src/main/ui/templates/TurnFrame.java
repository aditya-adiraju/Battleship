package ui.templates;

import model.BattleShip;
import model.Player;
import ui.Game;
import ui.util.mappanels.TurnMapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnFrame implements ActionListener {
    Game game;
    TurnMapPanel mapPanel;
    GameFrame gameFrame;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenuItem saveItem;
    JMenuItem newGameItem;
    JMenuItem exitItem;


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: initializes new turn for given game and sets up associated maps
    public TurnFrame(Game game) {
        this.game = game;
        mapPanel = new TurnMapPanel(this);

        renderFrame();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: renders the final frame to be displayed
    private void renderFrame() {
        JPanel controlPanel = renderControlPanel();
        gameFrame = new GameFrame();
        gameFrame.add(controlPanel);
        gameFrame.setJMenuBar(menuBar);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: renders a JPanel with both maps
    private JPanel renderControlPanel() {
        Player player;
        JPanel enclosingPanel = new JPanel();
        initializeMenu();
        enclosingPanel.setLayout(new GridLayout(2, 1, 50, 50));
        if (game.getCurrentPlayer() == 0) {
            player = game.getPlayer1();
        } else {
            player = game.getPlayer2();
        }
        enclosingPanel.add(mapPanel.renderMap(player.getRadarBoard(), false));
        enclosingPanel.add(mapPanel.renderMap(player.getOceanBoard(), true));
        return enclosingPanel;
    }

    private void initializeMenu() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        saveItem = new JMenuItem("Save");
        newGameItem = new JMenuItem("New Game");
        exitItem = new JMenuItem("Exit");

        newGameItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        fileMenu.add(saveItem);
        fileMenu.add(newGameItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }



    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: ends the players turn moves to next player
    public void endTurn() {
        game.changeCurrentPlayer();
        this.gameFrame.dispose();
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: check whether the game is over and ends turn if needed
    public void checkEndGame() {
        if (game.isOver() == 1) {
            JOptionPane.showMessageDialog(null, "Player 1 Wins!",
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            this.gameFrame.dispose();
        } else if (game.isOver() == 2) {
            JOptionPane.showMessageDialog(null, "Player 2 Wins!",
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            this.gameFrame.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveItem) {
            game.saveGame();
            System.exit(0);
        } else if (e.getSource() == newGameItem) {
            this.gameFrame.dispose();
            game = initializeGame();
            renderFrame();
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    private Game initializeGame() {
        Game game = new Game("Eve", "Fran", 5);

        BattleShip s = new BattleShip(2);
        s.rotate();
        game.getPlayer1().placeShip(new BattleShip(2), 3, 2);
        game.getPlayer2().placeShip(s,3, 4);
        game.getPlayer1().launchAttack(game.getPlayer2(), 0, 1);
        game.getPlayer2().launchAttack(game.getPlayer1(), 0, 0);
        return game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}