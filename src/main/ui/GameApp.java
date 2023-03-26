package ui;

import model.BattleShip;
import model.Score;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// CLASS-LEVEL COMMENT: GameApp for user to interact with and start/load games
public class GameApp {

    public static final String GAME_PATH = "./data/game.json";
    private static final String SCORE_PATH = "./data/scores.json";
    Game game;
    List<Score> scoreList = loadScores();


    // EFFECTS: constructs an instance of a new Game App.
    public GameApp() {
        System.out.println("BATTLESHIP: THE GAME!!");
        playNewGame();
    }


    // EFFECTS: displays a set of options for the user to choose from
    private void displayMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("n\tStart a new game");
        System.out.println("l\tload from game");
        System.out.print("Choose an option: ");
    }


    // EFFECTS: prints out the scores between all players
    private void displayScores(List<Score> scores) {
        System.out.println("THE SCOREBOARD");
        for (Score s : scores) {
            System.out.println(s.getName() + ": " + s.getPoints());
        }
    }

    // EFFECTS: add a new a score to the list of scores
    private void addScores(String name, int points) {
        for (Score s : scoreList) {
            if (s.getName().equals(name)) {
                s.setPoints(points);
                return;
            }
        }
        scoreList.add(new Score(name, points));
    }

    // EFFECTS: increments a score of a given player
    private void incrementScores(String name) {
        for (Score s : scoreList) {
            if (s.getName().equals(name)) {
                s.setPoints(s.getPoints() + 1);
                return;
            }
        }
        scoreList.add(new Score(name, 1));
    }


    // EFFECTS: parses user input and performs appropriate task
    private boolean processCommand() {
        Scanner in = new Scanner(System.in);
        displayMenu();
        char command = in.nextLine().charAt(0);
        if (command == 'n') {
            System.out.print("Enter player 1's name: ");
            String p1Name = in.nextLine();
            System.out.print("Enter player 2's name: ");
            String p2Name = in.nextLine();
            return playGame(p1Name, p2Name);
        } else if (command == 'l') {
            loadGame();
            return playGame(game);
        } else {
            return false;
        }
    }

    // EFFECTS: plays a new game of battleship
    private void playNewGame() {
        boolean gameResult;
        Scanner in = new Scanner(System.in);
        while (true) {
            gameResult = processCommand();
            if (gameResult) {
                incrementScores(game.player1.getUsername());
            } else {
                incrementScores(game.player2.getUsername());
            }
            displayScores(scoreList);
            saveScores();
            System.out.println("Would you like to continue playing [y/n]");
            char res = in.nextLine().toLowerCase().charAt(0);
            if (res != 'y') {
                break;
            }
        }
        System.out.println("GoodBye!");
    }

    // EFFECTS: initiates a Game between the players
    public boolean playGame(String p1Name, String p2Name) {
        int size;
        size = getSizeFromUser();
        System.out.println("WELCOME TO A NEW GAME OF BATTLESHIP");
        game = new Game(p1Name, p2Name, size);
        return game.playGame(true);
    }


    // EFFECTS: continues playing a given game of battleship
    public boolean playGame(Game g) {
        return g.playGame();
    }

    // EFFECTS: gets and returns the game size from user
    private int getSizeFromUser() {
        Scanner in = new Scanner(System.in);
        int size;
        System.out.print("Enter a game board size between 5 and 26: ");
        size = in.nextInt();
        while (size < 5 || size > 26) {
            System.out.println("TRY AGAIN");
            System.out.print("Enter a game board size between 5 and 26: ");
            size = in.nextInt();
        }
        return size;
    }


    // EFFECTS: loads a saved game from game.json
    void loadGame() {
        JsonReader jsonReader = new JsonReader(GAME_PATH);
        try {
            game = jsonReader.readGame();
        } catch (IOException e) {
            System.out.println("Cannot find a saved file");
        }
    }

    void saveScores() {
        JSONArray scoreListJson = new JSONArray();
        for (Score s : scoreList) {
            scoreListJson.put(s.toJson());
        }
        JsonWriter w = new JsonWriter(SCORE_PATH);
        try {
            w.open();
            w.writeScoreList(scoreListJson);
            w.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Write to file, path not found: " + SCORE_PATH);
        }
    }

    List<Score> loadScores() {
        JsonReader jsonReader = new JsonReader(SCORE_PATH);
        List<Score> scores;
        try {
            return jsonReader.readScoreList();
        } catch (IOException e) {
            System.out.println("Cannot find a saved file");
        }
        return new ArrayList<>();
    }

}

