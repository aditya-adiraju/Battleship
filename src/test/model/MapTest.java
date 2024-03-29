package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    Map m1;
    Map m2;
    final String EMPTY_SQUARE = Map.EMPTY_SQUARE;

    @BeforeEach
    void setup() {
        m1 = new OceanMap(12);
        m2 = new RadarMap(10);
    }

    @Test
    void testSetBoard() {
        String[][] er1 = new String[12][12];
        for(String[] row: er1) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        er1[0][0] = OceanMap.SUNKEN_SHIP;
        m1.setBoard(er1);
        assertArrayEquals(er1, m1.getBoard());

        String[][] er2 = new String[10][10];
        for(String[] row: er2) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        er1[2][1] = RadarMap.HIT_MISSILE;
        m1.setBoard(er2);
        assertArrayEquals(er2, m2.getBoard());
    }
    @Test
    void testSetCoordinate() {
        String[][] er1 = new String[12][12];
        for(String[] row: er1) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        String[][] er2 = new String[10][10];
        for(String[] row: er2) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        er2[0][1] = "O";
        m2.setCoordinate("O", 1, 0);
        assertArrayEquals(er2, m2.getBoard());

        er1[5][4] = "x";
        m1.setCoordinate("x", 4, 5);
        assertArrayEquals(er1, m1.getBoard());


    }
}
