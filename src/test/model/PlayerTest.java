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
    Player p3;
    BattleShip bs1;
    BattleShip bs2;
    String[][] expectedResult1;
    String[][] expectedResult2;
    final String EMPTY_SQUARE = Map.EMPTY_SQUARE;
    final String SHIP = OceanMap.SHIP;
    final String SUNKEN_SHIP = OceanMap.SUNKEN_SHIP;

    @BeforeEach
    void setup() {
        p1 = new Player("Alice", 12);
        p2 = new Player("Bob", 10);
        p3 = new Player("Charles", 12);
        bs1 = new BattleShip(4);
        bs2 = new BattleShip(1);
        expectedResult1 = new String[12][12];
        for(String[] row: expectedResult1) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        expectedResult2 = new String[10][10];
        for(String[] row: expectedResult2) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
    }

    @Test
    void testConstructor() {
        assertEquals("Alice", p1.getUsername());
        assertArrayEquals(expectedResult1, p1.getOceanBoard());
        assertArrayEquals(expectedResult1, p1.getRadarBoard());
        assertEquals(new ArrayList<BattleShip>(), p1.getShips());

        assertEquals("Bob", p2.getUsername());
        assertArrayEquals(expectedResult2, p2.getOceanBoard());
        assertArrayEquals(expectedResult2, p2.getRadarBoard());
        assertEquals(new ArrayList<BattleShip>(), p2.getShips());
    }

    @Test
    void testPlaceShip() {
        bs1.rotate();
        p1.placeShip(bs1, 3, 5);

        expectedResult1[5][3] = SHIP;
        expectedResult1[6][3] = SHIP;
        expectedResult1[7][3] = SHIP;
        expectedResult1[8][3] = SHIP;

        assertArrayEquals(expectedResult1, p1.getOceanBoard());
        assertEquals(new ArrayList<>(Collections.singletonList(bs1)), p1.getShips());

        assertFalse(p1.placeShip(bs1, 3, 6));
    }

    @Test
    void testHitTarget() {
        bs1.rotate();
        p1.placeShip(bs1 ,1, 2);
        p1.placeShip(bs2, 0, 0);
        assertFalse(p1.hitTarget(11, 11));
        assertTrue(p1.hitTarget(1, 2));
        assertTrue(p1.hitTarget(1, 3));
        assertTrue(p1.hitTarget(0, 0));
        assertEquals(new ArrayList<BattleShip>(Collections.singletonList(bs1)), p1.getShips());
        assertTrue(p1.hitTarget(1, 4));
        assertTrue(p1.hitTarget(1, 5));
        assertEquals(new ArrayList<BattleShip>(), p1.getShips());

    }

    @Test
    void testLaunchAttack() {
        assertTrue(p3.placeShip(bs1, 0, 2));
        assertFalse(p1.launchAttack(p3, 0, 0));
        assertTrue(p1.launchAttack(p3, 0, 2));
        assertFalse(p1.launchAttack(p3, 0, 2));
        assertTrue(p1.launchAttack(p3, 1, 2));
        assertTrue(p1.launchAttack(p3, 2, 2));
        assertTrue(p1.launchAttack(p3, 3, 2));
        assertFalse(p1.launchAttack(p3, 4, 2));
    }

    @Test
    void testIsOver() {
        assertTrue(p1.isLose());
        assertTrue(p1.placeShip(bs1, 3, 4));
        assertFalse(p1.isLose());
        assertTrue(p1.hitTarget(3, 4));
        assertTrue(p1.hitTarget(4, 4));
        assertTrue(p1.hitTarget(5, 4));
        assertTrue(p1.hitTarget(6, 4));
        assertTrue(p1.isLose());

    }

    @Test
    void testGetMaximumShips() {
        assertEquals(7, p1.getMaximumShips());
        assertEquals(5, p2.getMaximumShips());
        assertEquals(7, p3.getMaximumShips());
    }

}
