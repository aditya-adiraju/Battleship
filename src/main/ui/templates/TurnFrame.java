package ui.templates;

import model.BattleShip;
import model.Player;
import ui.CLI;
import ui.Game;
import ui.util.mappanels.TurnMapPanel;
import ui.util.textpanels.PlayerInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.templates.TitleScreenFrame.loadGame;

// CLASS-LEVEL COMMENT: A frame that displays when playing the battle phase of the game
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
    JMenuItem loadGameItem;
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
        gameFrame.setLayout(new GridLayout(1, 2));
        PlayerInfoPanel pip = new PlayerInfoPanel(getActualPlayer(), true);
        gameFrame.add(controlPanel);
        gameFrame.add(pip.renderPlayerPanel());
        gameFrame.setJMenuBar(menuBar);
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

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: sets up all the options in the menu
    private void initializeMenu() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        saveItem = new JMenuItem("Save");
        newGameItem = new JMenuItem("New Game");
        loadGameItem = new JMenuItem("Load Game");
        exitItem = new JMenuItem("Exit");

        newGameItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        loadGameItem.addActionListener(this);

        fileMenu.add(saveItem);
        fileMenu.add(newGameItem);
        fileMenu.add(loadGameItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }



    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: ends the players turn moves to next player
    public void endTurn() {
        passPlayer();
        game.changeCurrentPlayer();
        renderFrame();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: check whether the game is over and ends turn if needed
    public void checkEndGame() {
        int gameResult = game.isOver();
        if (gameResult == 1) {
            JOptionPane.showMessageDialog(null, "Player 2 Wins!",
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            this.gameFrame.dispose();
            CLI.incrementScores(game.getPlayer2().getUsername());
            CLI.saveScores();
            new HighScoresFrame();
        } else if (gameResult == 2) {
            JOptionPane.showMessageDialog(null, "Player 1 Wins!",
                    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
            this.gameFrame.dispose();
            CLI.saveScores();
            CLI.incrementScores(game.getPlayer1().getUsername());
            new HighScoresFrame();
        }
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: routes correct method for each menu item and button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveItem) {
            game.saveGame();
            JOptionPane.showMessageDialog(null, "Game saved successfully.",
                    "Save Complete", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (e.getSource() == newGameItem) {
            this.gameFrame.dispose();
            new TitleScreenFrame();
        } else if (e.getSource() == loadGameItem) {
            this.gameFrame.dispose();
            JOptionPane.showMessageDialog(null, "Game loaded successfully",
                    "Game loaded!", JOptionPane.INFORMATION_MESSAGE);
            new TurnFrame(loadGame());
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
}