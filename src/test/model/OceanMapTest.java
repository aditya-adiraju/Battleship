package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OceanMapTest {
    OceanMap m1;
    OceanMap m2;
    String[][] defaultMap1;
    String[][] defaultMap2;
    BattleShip bs;
    final String EMPTY_SQUARE = Map.EMPTY_SQUARE;
    final String SHIP = OceanMap.SHIP;
    final String SUNKEN_SHIP = OceanMap.SUNKEN_SHIP;

    @BeforeEach
    void setup() {
        m1 = new OceanMap(10);
        defaultMap1 = new String[10][10];
        for(String[] row: defaultMap1) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        m2 = new OceanMap(13);
        defaultMap2 = new String[13][13];
        for(String[] row: defaultMap2) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        bs = new BattleShip(4);
    }

    @Test
    void testConstructor() {
        assertArrayEquals(defaultMap1, m1.getBoard());
        assertEquals(0, m1.getNumberOfShips());
        assertEquals(5, m1.getMaxNumberOfShips());

        assertArrayEquals(defaultMap2, m2.getBoard());
        assertEquals(0, m2.getNumberOfShips());
        assertEquals(8, m2.getMaxNumberOfShips());
    }

    @Test
    void testPlaceShip() {
        bs.rotate();
        assertTrue(m1.placeShip(bs, 2, 4));
        defaultMap1[4][2] = SHIP;
        defaultMap1[5][2] = SHIP;
        defaultMap1[6][2] = SHIP;
        defaultMap1[7][2] = SHIP;

        assertArrayEquals(defaultMap1, m1.getBoard());
        assertEquals(1, m1.getNumberOfShips());
    }

    @Test
    void testPlaceShipOutOfBounds() {
        assertFalse(m2.placeShip(bs, 12, 12));
        assertFalse(m2.placeShip(bs, 11, 0));
        assertFalse(m2.placeShip(bs, 10, 0));
        assertArrayEquals(defaultMap2, m2.getBoard());
    }
    @Test
    void testIsHit() {

        m1.placeShip(bs, 4, 4);

        assertFalse(m1.isHit(3, 3));

        assertTrue(m1.isHit(4, 4));
        assertFalse(m1.isHit(4, 4));
        assertTrue(m1.isHit(5, 4));
        assertTrue(m1.isHit(6, 4));
        assertTrue(m1.isHit(7, 4));

        assertFalse(m1.isHit(8, 4));

    }
}