package ui;

import persistence.JsonReader;

import java.io.IOException;
import java.util.Scanner;

// CLASS-LEVEL COMMENT: GameApp for user to interact with and start/load games
public class GameApp {

    public static final String GAME_PATH = "./data/game.json";
    Game game;
    int p1Score = 0;
    int p2Score = 0;

    public GameApp() {

        System.out.println("BATTLESHIP: THE GAME!!");
        playNewGame();
    }

    private void displayMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("n\tStart a new game");
        System.out.println("l\tload from game");
        System.out.print("Choose an option: ");
    }


    // EFFECTS: prints out the scores between p1 and p2
    private void displayScores(String p1Name, String p2Name, int p1Score, int p2Score) {
        System.out.println("THE SCOREBOARD");
        System.out.println(p1Name + ": " + p1Score);
        System.out.println(p2Name + ": " + p2Score);
    }

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

    private void playNewGame() {
        boolean gameResult;
        Scanner in = new Scanner(System.in);
        while (true) {
            gameResult = processCommand();
            if (gameResult) {
                p1Score++;
            } else {
                p2Score++;
            }
            displayScores(game.player1.getUsername(), game.player2.getUsername(), p1Score, p2Score);
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

    void loadGame() {
        JsonReader jsonReader = new JsonReader(GAME_PATH);
        try {
            game = jsonReader.readGame();
        } catch (IOException e) {
            System.out.println("Cannot find a saved file");
        }
    }

}

