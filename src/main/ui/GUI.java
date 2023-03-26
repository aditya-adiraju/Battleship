package ui;

import model.BattleShip;
import persistence.JsonReader;
import ui.templates.SelectionFrame;
import ui.templates.TurnFrame;

import java.io.IOException;

import static ui.GameApp.GAME_PATH;

public class GUI {

    public GUI() {
        Game game = initializeGame();
        new SelectionFrame(game);
    }

    private Game initializeGame() {
        Game game = new Game("Eve", "Fran", 5);
        return game;
    }

    // EFFECTS: loads a saved game from game.json
    Game loadGame() {
        JsonReader jsonReader = new JsonReader(GAME_PATH);
        try {
            return jsonReader.readGame();
        } catch (IOException e) {
            System.out.println("Cannot find a saved file");
        }
        return null;
    }

}
