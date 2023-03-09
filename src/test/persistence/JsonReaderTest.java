package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void readGameFromRegularGame() {
        JsonReader reader = new JsonReader("./data/testReaderRegularGame.json");
        try {
            reader.readGame();
        } catch (IOException e) {
            fail("This file should exist... Something is off. oopsie!");
        }
    }

    @Test
    void readGameFromNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakefiles4days.json");
        try {
            reader.readGame();
            fail("IOException was supposed to show up");
        } catch (IOException e) {
            // Passing all good :)))
        }
    }

    @Test
    void readGameFromInitializedGame() {
        JsonReader reader = new JsonReader("./data/testReaderInitializedGame.json");
        try {
            reader.readGame();
        } catch (IOException e) {
            fail("This file should exist... Something is off. oopsie!");
        }
    }
}
