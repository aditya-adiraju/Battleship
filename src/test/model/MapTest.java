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

        er1[4][5] = "x";
        m1.setCoordinate("x", 4, 5);
        assertEquals(er1, m1.getBoard());

        er2[1][0] = "O";
        m1.setCoordinate("O", 1, 0);
        assertEquals(er2, m1.getBoard());
    }
}
