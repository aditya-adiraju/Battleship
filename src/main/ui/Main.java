package ui;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int size;
        Scanner in = new Scanner(System.in);

        System.out.println("BATTLESHIP: THE GAME!!");

        System.out.print("Enter player 1's name: ");
        String p1Name = in.nextLine();
        System.out.print("Enter player 2's name: ");
        String p2Name = in.nextLine();


        size = getSizeFromUser();

        Game g = new Game(p1Name, p2Name, size);

        g.playGame();
    }

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
