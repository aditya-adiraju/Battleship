package ui;

import model.BattleShip;
import model.Player;

import javax.lang.model.type.ArrayType;
import java.util.List;
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
            BattleShip s = new BattleShip(shipSizes[i % shipSizes.length]);
            System.out.println(p.getUsername() + "\'s Ocean Map");
            displayBoard(p.getOceanBoard());
            displayBoard(s.getShipBoard());
            System.out.println();
            getAndPlaceShip(p, s);
        }
    }


    // EFFECTS: retrieves a set of x, y coordinates from user
    private int[] getCoordinate() {
        int x;
        int y;
        System.out.print("Enter the row you want to place the marked point of your ship on your board[A, B, C, etc]: ");
        y = in.next().toUpperCase().charAt(0);
        y -= 65;
        System.out.print("Enter the column you want to place the marked point on your board [1, 2, 3, etc.]: ");
        x = in.nextInt();
        return new int[] {x, y};
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds ships to given player's board
    private void getAndPlaceShip(Player p, BattleShip s) {
        int x;
        int y;
        int[] coordinates;
        getRotation(s);
        coordinates = getCoordinate();
        x = coordinates[0];
        y = coordinates[1];
        while (x < 0 || y < 0 || x >= size || x >= size) {
            System.out.println("Enter a valid coordinate!");
            getRotation(s);
            coordinates = getCoordinate();
            x = coordinates[0];
            y = coordinates[1];
        }
        p.placeShip(s, x, y);
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
    
    public void play() {
        intitBoard(player1);
        intitBoard(player2);
    }



}
