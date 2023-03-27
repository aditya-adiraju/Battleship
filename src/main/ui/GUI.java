package ui;

import persistence.JsonReader;
import ui.templates.HighScoresFrame;
import ui.templates.TitleScreenFrame;

import java.io.IOException;

import static ui.CLI.GAME_PATH;

public class GUI {

    public GUI() {
        new HighScoresFrame();
    }
}
