package ui;

import model.Score;

import java.io.IOException;

// CLASS-LEVEL COMMENT: represents an instance of a new program or game of battleship
public class Main {
    public static void main(String[] args) {
        try {
            new GUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
