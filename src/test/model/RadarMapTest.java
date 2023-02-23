package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RadarMapTest {
    Player opp;
    RadarMap m1;
    RadarMap m2;
    String[][] defaultMap1;
    String[][] defaultMap2;
    BattleShip bs;

    @BeforeEach
    void setup() {
        m1 = new RadarMap(10);
        defaultMap1 = new String[10][10];
        for(String[] row: defaultMap1) {
            Arrays.fill(row, " ");
        }
        opp = new Player("Charlie",10);
        m2 = new RadarMap(15);
        defaultMap2 = new String[15][15];
        for(String[] row: defaultMap2) {
            Arrays.fill(row, " ");
        }
        bs = new BattleShip(4);
    }

    @Test
    void testConstructor() {
        assertArrayEquals(defaultMap1, m1.getBoard());
        assertArrayEquals(defaultMap2, m2.getBoard());
    }



    @Test
    void testLaunchAttack() {

        opp.placeShip(bs, 4, 4);

        assertFalse(m1.launchMissile(opp, 3, 3));
        assertTrue(m1.launchMissile(opp, 4, 4));
        assertFalse(m1.launchMissile(opp, 4, 4));
        assertTrue(m1.launchMissile(opp, 5, 4));
        assertTrue(m1.launchMissile(opp, 6, 4));
        assertTrue(m1.launchMissile(opp, 7, 4));
        assertFalse(m1.launchMissile(opp, 8, 4));

        defaultMap1[3][3] = "O";
        defaultMap1[4][4] = "X";
        defaultMap1[4][5] = "X";
        defaultMap1[4][6] = "X";
        defaultMap1[4][7] = "X";
        defaultMap1[4][8] = "O";

        assertArrayEquals(defaultMap1, m1.getBoard());
    }
}
