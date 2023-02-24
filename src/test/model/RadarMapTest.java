package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RadarMapTest {
    Player opp;
    RadarMap m1;
    RadarMap m2;
    String[][] defaultMap1;
    String[][] defaultMap2;
    BattleShip bs;
    final String EMPTY_SQUARE = Map.EMPTY_SQUARE;
    final String HIT_SQUARE = RadarMap.HIT_MISSILE;
    final String MISSED_SQUARE = RadarMap.MISSED_MISSILE;

    @BeforeEach
    void setup() {
        m1 = new RadarMap(10);
        defaultMap1 = new String[10][10];
        for(String[] row: defaultMap1) {
            Arrays.fill(row, EMPTY_SQUARE);
        }
        opp = new Player("Charlie",10);
        m2 = new RadarMap(15);
        defaultMap2 = new String[15][15];
        for(String[] row: defaultMap2) {
            Arrays.fill(row, EMPTY_SQUARE);
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

        defaultMap1[3][3] = MISSED_SQUARE;
        defaultMap1[4][4] = HIT_SQUARE;
        defaultMap1[4][5] = HIT_SQUARE;
        defaultMap1[4][6] = HIT_SQUARE;
        defaultMap1[4][7] = HIT_SQUARE;
        defaultMap1[4][8] = MISSED_SQUARE;

        assertArrayEquals(defaultMap1, m1.getBoard());
    }
}
