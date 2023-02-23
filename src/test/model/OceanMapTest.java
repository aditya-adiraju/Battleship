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

    @BeforeEach
    void setup() {
        m1 = new OceanMap(10);
        defaultMap1 = new String[10][10];
        for(String[] row: defaultMap1) {
            Arrays.fill(row, " ");
        }
        m2 = new OceanMap(13);
        defaultMap2 = new String[13][13];
        for(String[] row: defaultMap2) {
            Arrays.fill(row, " ");
        }
        bs = new BattleShip(4);
    }

    @Test
    void testConstructor() {
        assertArrayEquals(defaultMap1, m1.getBoard());
        assertEquals(0, m1.getMaxNumberOfShips());
        assertEquals(5, m1.maxNumberOfShips);

        assertArrayEquals(defaultMap2, m2.getBoard());
        assertEquals(0, m2.getMaxNumberOfShips());
        assertEquals(8, m2.maxNumberOfShips);
    }

    @Test
    void testPlaceShip() {
        bs.rotate();
        m1.placeShip(bs, 2, 4);
        defaultMap1[4][2] = "□";
        defaultMap1[5][2] = "□";
        defaultMap1[6][2] = "□";
        defaultMap1[7][2] = "□";

        assertArrayEquals(defaultMap1, m1.getBoard());
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
    @Test
    void testIsOver() {
        assertTrue(m1.isOver());
        m1.placeShip(bs, 2,2);
        assertFalse(m1.isOver());
    }
}