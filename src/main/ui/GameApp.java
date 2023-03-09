package ui;

import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// CLASS-LEVEL COMMENT: Game App for user to interact with and start/load games
public class GameApp {

    public static final String GAME_PATH = "./data/game.json";
    Game game;

    public GameApp() {
        int p1Score = 0;
        int p2Score = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("BATTLESHIP: THE GAME!!");
        System.out.print("Enter player 1's name: ");
        String p1Name = in.nextLine();
        System.out.print("Enter player 2's name: ");
        String p2Name = in.nextLine();

        while (true) {
            int gameResult = playGame(p1Name, p2Name)
            if (gameResult == 0) {
                p2Score++;
            } else if (gameResult == 1) {
                p1Score++;
            } else {
                saveGame();
                System.exit(0);
            }
            displayScores(p1Name, p2Name, p1Score, p2Score);
            System.out.println("Would you like to continue playing [y/n]");
            char res = in.nextLine().toLowerCase().charAt(0);
            if (res != 'y') {
                break;
            }
        }
        System.out.println("GoodBye!");
    }

    // EFFECTS: prints out the scores between p1 and p2
    private void displayScores(String p1Name, String p2Name, int p1Score, int p2Score) {
        System.out.println("THE SCOREBOARD");
        System.out.println(p1Name + ": " + p1Score);
        System.out.println(p2Name + ": " + p2Score);
    }

    // EFFECTS: initiates a Game between the players
    public int playGame(String p1Name, String p2Name) {
        int size;
        size = getSizeFromUser();
        System.out.println("WELCOME TO A NEW GAME OF BATTLESHIP");
        game = new Game(p1Name, p2Name, size);
        return game.playGame();
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

    void saveGame() {
        JsonWriter w = new JsonWriter(GAME_PATH);
        try {
            w.open();
            w.write(game);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Write to file, path not found: " + GAME_PATH);
        }
    }

    void loadGame() {

    }
}
