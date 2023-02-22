package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player p1;
    Player p2;
    BattleShip bs1;
    String[][] expectedResult1;
    String[][] expectedResult2;

    @BeforeEach
    void setup() {
        Player p1 = new Player("Alice", 12);
        Player p2 = new Player("Bob", 10);
        BattleShip bs1 = new BattleShip(4);
        String[][] expectedResult1 = new String[12][12];
        for(String[] row: expectedResult1) {
            Arrays.fill(row, " ");
        }
        String[][] expectedResult2 = new String[10][10];
        for(String[] row: expectedResult2) {
            Arrays.fill(row, " ");
        }
    }

    @Test
    void testConstructor() {
        assertEquals("Alice", p1.getUsername());
        assertEquals(expectedResult1, p1.getOceanMap());
        assertEquals(expectedResult1, p1.getRadarMap());
        assertEquals(new ArrayList<BattleShip>(), p1.getShips());

        assertEquals("Bob", p2.getUsername());
        assertEquals(expectedResult2, p2.getOceanMap());
        assertEquals(expectedResult2, p2.getRadarMap());
        assertEquals(new ArrayList<BattleShip>(), p2.getShips());
    }

    @Test
    void testPlaceShip() {
        bs1.rotate();
        p1.placeShip(bs1, 3, 5);

        expectedResult1[3][5] = "□";
        expectedResult1[3][6] = "□";
        expectedResult1[3][7] = "□";
        expectedResult1[3][8] = "□";

        assertEquals(expectedResult1, p1.getOceanMap());
        assertEquals(new ArrayList<>(Collections.singletonList(bs1)), p1.getShips());
    }

    @Test
    void testHitTarget() {
        p1.placeShip(bs1 ,1, 2);

        assertFalse(p1.hitTarget(0, 0));
        assertFalse(p1.hitTarget(11, 11));
        assertTrue(p1.hitTarget(1, 2));
        assertTrue(p1.hitTarget(1, 3));
        assertTrue(p1.hitTarget(1, 4));
        assertTrue(p1.hitTarget(1, 5));

        assertEquals(new ArrayList<BattleShip>(), p1.getShips());
    }
}
