package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.Map.EMPTY_SQUARE;
import static model.OceanMap.SHIP;
import static model.OceanMap.SUNKEN_SHIP;
import static org.junit.jupiter.api.Assertions.*;

class BattleShipTest {

    BattleShip bs1;
    BattleShip bs2;
    BattleShip bs3;
    BattleShip bs4;
    BattleShip bs5;

    @BeforeEach
    void setup() {
        bs1 = new BattleShip(1);
        bs2 = new BattleShip(2);
        bs3 = new BattleShip(3);
        bs4 = new BattleShip(4);
        bs5 = new BattleShip(5);
    }

    @Test
    void testConstructor() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(0, 0));

        assertEquals(expectedResult, bs1.getCoordinates());
        assertEquals(1, bs1.getSize());

        expectedResult.add(new Point(1, 0));
        expectedResult.add(new Point(2, 0));
        expectedResult.add(new Point(3, 0));
        expectedResult.add(new Point(4, 0));

        assertEquals(expectedResult, bs5.getCoordinates());
        assertEquals(5, bs5.getSize());
    }

    @Test
    void testRotate() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(1, -1));
        expectedResult.add(new Point(1, 0));
        expectedResult.add(new Point(1, 1));
        bs3.rotate();
        assertEquals(expectedResult, bs3.getCoordinates());
    }

    @Test
    void testRotate4Times() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(0, 0));
        expectedResult.add(new Point(1, 0));
        expectedResult.add(new Point(2, 0));

        bs3.rotate();
        bs3.rotate();
        bs3.rotate();
        bs3.rotate();

        assertEquals(expectedResult, bs3.getCoordinates());
    }

    @Test
    void testRotateBothNonZeroCoordinates() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(5, 2));
        expectedResult.add(new Point(5, 3));
        expectedResult.add(new Point(5, 4));
        expectedResult.add(new Point(5, 5));

        bs4.translate(3, 4);
        bs4.rotate();
        assertEquals(expectedResult, bs4.getCoordinates());
    }

    @Test
    void testTranslate() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(3, 7));
        expectedResult.add(new Point(4, 7));

        bs2.translate(3, 7);
        assertEquals(expectedResult, bs2.getCoordinates());
    }

    @Test
    void testRotateMultipleAndTranslate() {
        bs2.rotate();
        bs2.rotate();
        bs2.translate(5, 2);
        List<Point> result = bs2.getCoordinates();
        assertTrue(result.contains(new Point(5, 2)));
        assertTrue(result.contains(new Point(6, 2)));
        assertEquals(bs2.getSize(), result.size());
    }

    @Test
    void testRemoveCoordinate() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(1, 0));

        bs2.removeCoordinate(0, 0);

        assertEquals(expectedResult, bs2.getCoordinates());
    }

    @Test
    void testIsEmpty() {
      bs2.removeCoordinate(0, 0);
      bs2.removeCoordinate(1, 0);
      assertTrue(bs2.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(bs4.contains(1,1));
        assertTrue(bs4.contains(1,0));
        assertFalse(bs4.contains(0,1));
    }

    @Test
    void testGetShipBoard() {
        String[][] grid1 = new String[1][1];
        for(String[] row: grid1) {
            Arrays.fill(row, SUNKEN_SHIP);
        }
        String[][] grid3 = new String[3][3];
        for(String[] row: grid3) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        grid3[0][0] = SUNKEN_SHIP;
        grid3[1][0] = SHIP;
        grid3[2][0] = SHIP;

        bs3.rotate();
        assertArrayEquals(grid1, bs1.getShipBoard());
        assertArrayEquals(grid3, bs3.getShipBoard());
    }

    @Test
    void testEquals() {
        assertTrue(bs1.equals(bs1));
        assertFalse(bs1.equals(null));
        assertFalse(bs1.equals(bs2));
        BattleShip bsNew = new BattleShip(2);
        bsNew.removeCoordinate(0, 0);
        assertFalse(bs1.equals(bsNew));
        assertFalse(bs2.equals(bsNew));
        assertTrue(bs5.equals(new BattleShip(5)));
    }

    @Test
    void testHashCode() {
        assertEquals(1023, bs1.hashCode());
        assertEquals(1072695232, bs2.hashCode());
        assertEquals(-32475011, bs3.hashCode());
    }
}