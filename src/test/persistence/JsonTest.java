package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    // EFFECTS: a helper method that asserts if two players are equal
    void assertPlayerEquals(Player pExpected, Player pActual) {
        assertArrayEquals(pExpected.getOceanBoard(), pActual.getOceanBoard());
        assertArrayEquals(pExpected.getRadarBoard(), pActual.getRadarBoard());
        assertEquals(pExpected.getUsername(), pActual.getUsername());
        assertIterableEquals(pExpected.getShips(), pActual.getShips());
    }
}
