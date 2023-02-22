package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BattleShipTest {

    BattleShip bs1;
    BattleShip bs2;
    BattleShip bs3;
    BattleShip bs4;
    BattleShip bs5;

    @BeforeEach
    void setup() {
        BattleShip bs1 = new BattleShip(1);
        BattleShip bs2 = new BattleShip(2);
        BattleShip bs3 = new BattleShip(3);
        BattleShip bs4 = new BattleShip(4);
        BattleShip bs5 = new BattleShip(5);
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
        expectedResult.add(new Point(6, 1));
        expectedResult.add(new Point(5, 2));
        expectedResult.add(new Point(4, 3));
        expectedResult.add(new Point(3, 4));

        bs4.translate(2, 1);
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
    void testRemoveCoordinate() {
        List<Point> expectedResult = new ArrayList<>();
        expectedResult.add(new Point(0, 1));

        bs2.removeCoordinate(0, 0);
        assertEquals(expectedResult, bs2.getCoordinates());
    }

    @Test
    void testIsEmpty() {
      bs2.removeCoordinate(0, 0);
      bs2.removeCoordinate(1, 0);
      assertTrue(bs1.isEmpty());
    }
}