package ui;

import model.BattleShip;
import model.Player;
import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static model.Map.EMPTY_SQUARE;
import static model.OceanMap.*;
import static model.RadarMap.HIT_MISSILE;
import static model.RadarMap.MISSED_MISSILE;
import static ui.CLI.GAME_PATH;

// CLASS-LEVEL COMMENT:
// This represents a Game of Battleship with a size and 2 players
public class Game implements Writable {
    Player player1;
    Player player2;
    public static final String DEFAULT_NAME = "John Doe";
    int size;
    Scanner in = new Scanner(System.in);
    public static final int[] shipSizes = {2, 2, 3, 4, 5};
    int currentPlayer = 0;

    // EFFECTS: creates a new Game object with a player and size.
    public Game(String p1Name, String p2Name, int size) {
        player1 = new Player(p1Name, size);
        player2 = new Player(p2Name, size);
        this.size = size;
    }


    // EFFECTS: constructor of game with default names
    public Game(int size) {
        player1 = new Player(DEFAULT_NAME, size);
        player2 = new Player(DEFAULT_NAME, size);
        this.size = size;
    }

    // MODIFIES: this
    // EFFECTS: sets player1 to given Player object
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    // MODIFIES: this
    // EFFECTS: sets player2 to given Player object
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds ships to given player's board
    private void intitBoard(Player p) {
        System.out.println("You will now be placing ships on your map");
        for (int i = 0; i < p.getMaximumShips(); i++) {
            showOceanMap(p);
            BattleShip s = new BattleShip(shipSizes[i % shipSizes.length]);
            showShipMap(s);
            while (!getAndPlaceShip(p, s)) {
                System.out.println("CANNOT PLACE SHIP THERE");
            }
        }
    }

    // EFFECTS: renders a formatted view with legend of a ship.
    private void showShipMap(BattleShip s) {
        displayBoard(s.getShipBoard());
        System.out.println("SHIPBOARD LEGEND");
        System.out.print(EMPTY_SQUARE + ": an empty square\t");
        System.out.println(SHIP + ": ship");
        System.out.println(SUNKEN_SHIP + ": ship marker\n");
    }


    // EFFECTS: renders a formatted view of player's ocean map on the console
    private void showOceanMap(Player p) {
        System.out.println(p.getUsername() + "'s Ocean Map");
        displayBoard(p.getOceanBoard());
        displayLegend();
    }

    // EFFECTS: renders a formatted view of player's radar map on the console
    private void showRadarMap(Player p) {
        System.out.println(p.getUsername() + "'s Radar Map");
        displayBoard(p.getRadarBoard());

    }

    // EFFECTS: retrieves a set of x, y coordinates from user
    private int[] getCoordinate() {
        int x;
        int y;
        String coordinate;
        System.out.println("Enter the coordinate you want to place your marker on the board");
        System.out.print("[ex: A1, B2, C3, etc] There are no spaces in between: ");
        coordinate = in.next().toUpperCase();
        y = coordinate.charAt(0);
        y -= 65;
        try {
            x = Integer.parseInt(coordinate.substring(1));
        } catch (NumberFormatException e) {
            x = -1;
        }
        return new int[] {x, y};
    }

    // MODIFIES: this
    // EFFECTS: adds ships to given player's board, or fails
    private boolean getAndPlaceShip(Player p, BattleShip s) {
        int x;
        int y;
        int[] coordinates;
        coordinates = getValidCoordinates(s);
        x = coordinates[0];
        y = coordinates[1];
        return p.placeShip(s, x, y);
    }

    // MODIFIES: s
    // EFFECTS: rotates the ship if user wishes to, and does nothing otherwise.
    private void getRotation(BattleShip s) {
        System.out.print("Would you like to rotate the ship? [y/n]: ");
        char r = in.next().toUpperCase().charAt(0);
        if (r == 'Y') {
            s.rotate();
            displayBoard(s.getShipBoard());
        }
    }


    // EFFECTS: renders out a formatted board for a player
    private void displayBoard(String[][] grid) {
        char letter = 0x41;
        System.out.print("   ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (String[] row: grid) {
            System.out.print(letter + " ");
            for (String s: row) {
                System.out.print(s + " ");
            }
            System.out.print("\n");
            letter++;
        }
        System.out.println();

    }

    // EFFECTS: displays a key to understanding symbols
    private void displayLegend() {
        System.out.println("\n MAP LEGEND");
        System.out.print(EMPTY_SQUARE + ": an empty square\t");
        System.out.println(SHIP + ": an active ship");
        System.out.print(SUNKEN_SHIP + ": a sunken ship\t");
        System.out.println(MISSED_ATTACK + ": Missed enemy missile");
        System.out.print(HIT_MISSILE + ": Your missile sunk a ship\t");
        System.out.println(MISSED_MISSILE + ": Your missile missed\n");
    }

    // EFFECTS: anti-cheating mechanism to pass game between players
    private void passGame(Player p) {
        showRadarMap(p);
        showOceanMap(p);
        System.out.println("YOUR TURN HAS ENDED");
        System.out.println("PLEASE PRESS ENTER");
        in.nextLine();
        in.nextLine();
        clearConsole();
        System.out.println("PASS THE DEVICE TO YOUR OPPONENT, AND PRESS ENTER");
        in.nextLine();
        clearConsole();
    }

    // EFFECTS: hides opponents game from player by creating lines of blank text
    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    // EFFECTS: overloaded version of playGame when new game is played
    public boolean playGame(boolean isNew) {
        if (isNew) {
            initializeBoards();
        }
        return playGame();
    }

    // MODIFIES: this
    // EFFECTS: initialize game and go through each player's turn until win, return true if p1 wins
    public boolean playGame() {
        while (!player1.isLose() && !player2.isLose()) {
            System.out.println("Would you like to save your game and quit [y/n]");
            char res = in.nextLine().toLowerCase().charAt(0);
            if (res == 'y') {
                saveGame();
                System.exit(0);
            }
            playTurn(player1, player2);
            if (player1.isLose() || player2.isLose()) {
                break;
            }
            playTurn(player2, player1);
        }
        System.out.print("YOU WIN!!!!!!!!! ");
        if (player1.isLose()) {
            System.out.println(player2.getUsername());
            return false;
        } else {
            System.out.println(player1.getUsername());
            return true;
        }
    }

    // EFFECTS: plays a single turn for player against opponent
    private void playTurn(Player player, Player opp) {
        boolean attackSuccessful = true;
        while (attackSuccessful) {
            attackSuccessful = attack(player, opp);
        }
        passGame(player);
    }

    // EFFECTS: initialize the ships on both player's boards
    private void initializeBoards() {
        intitBoard(player1);
        passGame(player1);
        intitBoard(player2);
        passGame(player2);
    }

    // MODIFIES: this
    // EFFECTS: player p attacks opponent, returns true if successful
    private boolean attack(Player p, Player opp) {
        int x;
        int y;
        int[] coordinates;
        if (p.isLose() || opp.isLose()) {
            System.out.println(p.getUsername());
            return false;
        }
        showRadarMap(p);
        showOceanMap(p);
        System.out.println("Choose a point to attack \n");
        coordinates = getValidCoordinates();
        x = coordinates[0];
        y = coordinates[1];
        if (p.launchAttack(opp, x, y)) {
            System.out.println("YOU GOT A HIT!");
            return true;
        } else {
            System.out.println("YOU MISSED!");
            return false;
        }
    }

    // EFFECTS: gets coordinates that are valid for the game size, overloaded.
    private int[] getValidCoordinates() {
        return getValidCoordinates(null);
    }

    // MODIFIES: this
    // EFFECTS: get coordinates that are valid for the game size for a battleship
    private int[] getValidCoordinates(BattleShip s) {
        int x;
        int y;
        int[] coordinates;
        if (s != null) {
            getRotation(s);
        }
        coordinates = getCoordinate();
        x = coordinates[0];
        y = coordinates[1];
        while (x < 0 || y < 0 || x >= size || y >= size) {
            System.out.println("Enter a valid coordinate!");
            if (s != null) {
                getRotation(s);
            }
            coordinates = getCoordinate();
            x = coordinates[0];
            y = coordinates[1];
        }
        return coordinates;
    }


    // EFFECTS: saves a game to game.json
    public void saveGame() {
        JsonWriter w = new JsonWriter(GAME_PATH);
        try {
            w.open();
            w.write(this);
            w.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Write to file, path not found: " + GAME_PATH);
        }
    }

    // EFFECTS: returns a json object for a gamefile
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("size", size);
        json.put("player1", player1.toJson());
        json.put("player2", player2.toJson());
        return json;
    }

    public int getSize() {
        return size;
    }

    public int isOver() {
        if (player1.isLose()) {
            return 1;
        } else if (player2.isLose()) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void changeCurrentPlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}