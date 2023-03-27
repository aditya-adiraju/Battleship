package ui;

import persistence.JsonReader;
import ui.templates.HighScoresFrame;
import ui.templates.TitleScreenFrame;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static ui.CLI.GAME_PATH;

public class GUI {

    public GUI() throws IOException {
        new TitleScreenFrame();
    }
}
