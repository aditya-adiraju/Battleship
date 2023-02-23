package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    Map m1;
    Map m2;

    @BeforeEach
    void setup() {
        m1 = new OceanMap(12);
        m2 = new RadarMap(10);
    }

    @Test
    void testSetCoordinate() {
        String[][] er1 = new String[12][12];
        for(String[] row: er1) {
            Arrays.fill(row, " ");
        }
        String[][] er2 = new String[10][10];
        for(String[] row: er2) {
            Arrays.fill(row, " ");
        }
        er2[0][1] = "O";
        m2.setCoordinate("O", 1, 0);
        assertArrayEquals(er2, m2.getBoard());

        er1[5][4] = "x";
        m1.setCoordinate("x", 4, 5);
        assertArrayEquals(er1, m1.getBoard());


    }
}
