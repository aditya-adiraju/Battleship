package ui;

import model.BattleShip;
import model.Player;

import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;
    int size;
    Scanner in = new Scanner(System.in);
    private static final int[] shipSizes = {2, 2, 3, 4, 5};

    public Game(String p1Name, String p2Name, int size) {
        player1 = new Player(p1Name, size);
        player2 = new Player(p2Name, size);
        this.size = size;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds ships to given player's board
    public void intitBoard(Player p) {
        System.out.println("You will now be placing ships on your map");
        for (int i = 0; i < p.getMaximumShips(); i++) {
            showOceanMap(p);
            BattleShip s = new BattleShip(shipSizes[i % shipSizes.length]);
            displayBoard(s.getShipBoard());
            while (!getAndPlaceShip(p, s)) {
                System.out.println("CANNOT PLACE SHIP THERE");
            }
        }
    }

    private void showOceanMap(Player p) {
        System.out.println(p.getUsername() + "'s Ocean Map");
        displayBoard(p.getOceanBoard());
    }

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
        x = Character.getNumericValue(coordinate.charAt(1));
        return new int[] {x, y};
    }

    // REQUIRES:
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

    // REQUIRES:
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
    
    public void playGame() {
        boolean attackSuccesful = true;
        intitBoard(player1);
        intitBoard(player2);
        while (!player1.isOver() || !player2.isOver()) {

            while (attackSuccesful) {
                attackSuccesful = attack(player1, player2);
            }
            attackSuccesful = true;
            while (attackSuccesful) {
                attackSuccesful = attack(player2, player1);
            }
        }

        System.out.print("YOU WIN! ");
        if (player1.isOver()) {
            System.out.println(player2.getUsername());
        } else {
            System.out.println(player1.getUsername());
        }
        System.exit(0);
    }

    private boolean attack(Player p, Player opp) {
        int x;
        int y;
        int[] coordinates;
        if (p.isOver() || opp.isOver()) {
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
            showRadarMap(p);
            showRadarMap(p);
            System.out.println("YOU MISSED!");
            return false;
        }
    }

    //@Overload
    private int[] getValidCoordinates() {
        return getValidCoordinates(null);
    }

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
            if (s != null) {
                getRotation(s);
            }
            System.out.println("Enter a valid coordinate!");
            coordinates = getCoordinate();
            x = coordinates[0];
            y = coordinates[1];
        }
        return coordinates;
    }
}
