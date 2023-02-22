package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    Player p1;
    Player p2;
    BattleShip bs1;

    @BeforeEach
    void setup() {
        Player p1 = new Player("Alice", 12);
        Player p2 = new Player("Bob", 10);
        BattleShip bs1 = new BattleShip(4);
    }

    @Test
    void testConstructor() {
        assertEquals("Alice", p1.getUsername());
        String[][] expectedResult1 = new String[12][12];
        Arrays.fill(expectedResult1, " ");
        assertEquals(expectedResult1, p1.getOceanMap());
        assertEquals(expectedResult1, p1.getRadarMap());

        assertEquals("Bob", p2.getUsername());
        String[][] expectedResult2 = new String[10][10];
        Arrays.fill(expectedResult2, " ");
        assertEquals(expectedResult2, p2.getOceanMap());
        assertEquals(expectedResult2, p2.getRadarMap());
    }

    @Test
    void testPlaceShip() {
        bs1.rotate();
        p1.placeShip(bs1, 3, 5);
        String[][] expectedResult = new String[12][12];
        Arrays.fill(expectedResult, " ");
        expectedResult[3][5] = "□";
        expectedResult[3][6] = "□";
        expectedResult[3][7] = "□";
        expectedResult[3][8] = "□";

        assertEquals(expectedResult, p1.getOceanMap());
    }

    @Test
    void testHitTarget() {
        p1.placeShip(bs1 ,1, 2);

        assertFalse(p1.hitTarget(0, 0));
        assertFalse(p1.hitTarget(11, 11));
        assertTrue(p1.hitTarget(1, 2));
        assertTrue(p1.hitTarget(1, 3));
    }
}
