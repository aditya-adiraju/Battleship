package ui;

import persistence.JsonReader;
import ui.templates.HighScoresFrame;
import ui.templates.TitleScreenFrame;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static ui.CLI.GAME_PATH;

// CLASS-LEVEL COMMENT: GUI for the game
public class GUI {

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: renders the GUI of the game;
    public GUI() {
        new TitleScreenFrame();
    }
}
