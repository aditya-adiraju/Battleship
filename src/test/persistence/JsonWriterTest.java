package persistence;

import model.BattleShip;
import org.junit.jupiter.api.Test;
import ui.Game;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterNonExistentFile() {
        try {
            Game game = new Game("Ingrid", "Jacob", 10);
            JsonWriter writer = new JsonWriter("./data/fakeAndIllegal\0OwO.json");
            writer.open();
            fail("How did you even find this file, it shouldn't exist");
        } catch (FileNotFoundException e) {
            // All goooood :)
        }
    }

    @Test
    void testWriterEmptyGame() {
        try {
            Game game = new Game("Gaston", "Hayden", 6);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGame.json");

            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGame.json");
            Game newGame = reader.readGame();

            assertEquals(6, newGame.getSize());
            assertPlayerEquals(game.getPlayer1(), newGame.getPlayer1());
            assertPlayerEquals(game.getPlayer2(), newGame.getPlayer2());

        } catch (IOException e) {
            fail("The file path is supposed to be found");
        }
    }

    @Test
    void testWriterRegularGame() {
        try {
            Game game = new Game("Eve", "Fran", 5);
            JsonWriter writer = new JsonWriter("./data/testWriterRegularGame.json");
            BattleShip s = new BattleShip(2);
            s.rotate();
            game.getPlayer1().placeShip(new BattleShip(2), 3, 2);
            game.getPlayer2().placeShip(s,3, 4);

            game.getPlayer1().launchAttack(game.getPlayer2(), 0, 1);
            game.getPlayer2().launchAttack(game.getPlayer1(), 3, 2);
            game.getPlayer2().launchAttack(game.getPlayer1(), 0, 0);


            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRegularGame.json");
            Game newGame = reader.readGame();

            assertEquals(5, newGame.getSize());
            assertPlayerEquals(game.getPlayer1(), newGame.getPlayer1());
            assertPlayerEquals(game.getPlayer2(), newGame.getPlayer2());

        } catch (IOException e) {
            fail("The file path is supposed to be found");
        }
    }
}