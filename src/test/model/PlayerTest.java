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
        p1 = new Player("Alice", 12);
        p2 = new Player("Bob", 10);
        bs1 = new BattleShip(4);
        expectedResult1 = new String[12][12];
        for(String[] row: expectedResult1) {
            Arrays.fill(row, " ");
        }
        expectedResult2 = new String[10][10];
        for(String[] row: expectedResult2) {
            Arrays.fill(row, " ");
        }
    }

    @Test
    void testConstructor() {
        assertEquals("Alice", p1.getUsername());
        assertArrayEquals(expectedResult1, p1.getOceanMap());
        assertArrayEquals(expectedResult1, p1.getRadarMap());
        assertEquals(new ArrayList<BattleShip>(), p1.getShips());

        assertEquals("Bob", p2.getUsername());
        assertArrayEquals(expectedResult2, p2.getOceanMap());
        assertArrayEquals(expectedResult2, p2.getRadarMap());
        assertEquals(new ArrayList<BattleShip>(), p2.getShips());
    }

    @Test
    void testPlaceShip() {
        bs1.rotate();
        p1.placeShip(bs1, 3, 5);

        expectedResult1[5][3] = "□";
        expectedResult1[6][3] = "□";
        expectedResult1[7][3] = "□";
        expectedResult1[8][3] = "□";

        assertArrayEquals(expectedResult1, p1.getOceanMap());
        assertEquals(new ArrayList<>(Collections.singletonList(bs1)), p1.getShips());
    }

    @Test
    void testHitTarget() {
        bs1.rotate();
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
