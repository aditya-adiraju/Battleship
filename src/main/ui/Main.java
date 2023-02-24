package ui;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int p1Score = 0;
        int p2Score = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("BATTLESHIP: THE GAME!!");
        System.out.print("Enter player 1's name: ");
        String p1Name = in.nextLine();
        System.out.print("Enter player 2's name: ");
        String p2Name = in.nextLine();

        while (true) {
            if (playGame(p1Name, p2Name)) {
                p1Score++;
            } else {
                p2Score++;
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
    private static void displayScores(String p1Name, String p2Name, int p1Score, int p2Score) {
        System.out.println("THE SCOREBOARD");
        System.out.println(p1Name + ": " + p1Score);
        System.out.println(p2Name + ": " + p2Score);
    }

    // EFFECTS: initiates a Game between the players
    public static boolean playGame(String p1Name, String p2Name) {
        int size;
        size = getSizeFromUser();
        System.out.println("WELCOME TO A NEW GAME OF BATTLESHIP");
        Game g = new Game(p1Name, p2Name, size);
        return g.playGame();
    }

    // EFFECTS: gets and returns the game size from user
    private static int getSizeFromUser() {
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
}
