package persistence;

import model.BattleShip;
import model.OceanMap;
import model.Player;
import model.RadarMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    void readGameFromRegularGame() {
        JsonReader reader = new JsonReader("./data/testReaderRegularGame.json");
        try {
            Game game = reader.readGame();
            assertEquals(5, game.getSize());

            OceanMap omExpected1 = new OceanMap(5);
            omExpected1.setCoordinate(OceanMap.SHIP, 0, 0);
            omExpected1.setCoordinate(OceanMap.SHIP, 1, 0);
            omExpected1.setCoordinate(OceanMap.MISSED_ATTACK, 4, 0);
            omExpected1.setNumberOfShips(1);

            RadarMap rmExpected1 = new RadarMap(5);
            rmExpected1.setCoordinate(RadarMap.HIT_MISSILE, 0, 0);
            rmExpected1.setCoordinate(RadarMap.MISSED_MISSILE, 3, 1);
            BattleShip s1 = new BattleShip(2);

            List<BattleShip> sExpected1 = new ArrayList<>();
            sExpected1.add(s1);

            Player p1Expected = new Player("Charlie", omExpected1, rmExpected1, sExpected1);

            OceanMap omExpected2 = new OceanMap(5);
            omExpected2.setCoordinate(OceanMap.SUNKEN_SHIP, 0, 0);
            omExpected2.setCoordinate(OceanMap.SHIP, 1, 0);
            omExpected2.setCoordinate(OceanMap.MISSED_ATTACK, 3, 1);
            omExpected2.setNumberOfShips(1);

            RadarMap rmExpected2 = new RadarMap(5);
            rmExpected2.setCoordinate(RadarMap.MISSED_MISSILE, 4, 0);
            BattleShip s2 = new BattleShip(2);
            s2.removeCoordinate(0, 0);

            List<BattleShip> sExpected2 = new ArrayList<>();
            sExpected2.add(s2);

            Player p2Expected = new Player("David", omExpected2, rmExpected2, sExpected2);

            Player p1Actual = game.getPlayer1();
            Player p2Actual = game.getPlayer2();

            assertPlayerEquals(p1Expected, p1Actual);
            assertPlayerEquals(p2Expected, p2Actual);

        } catch (IOException e) {
            fail("This file should exist... Something is off. oopsie!");
        }
    }

    @Test
    void readGameFromNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakefiles4ever.json");
        try {
            reader.readGame();
            fail("IOException was supposed to show up");
        } catch (IOException e) {
            // Passing all good :)))
        }
    }

    @Test
    void readGameFromInitializedGame() {
        JsonReader reader = new JsonReader("./data/testReaderInitializedGame.json");
        try {
            Game game = reader.readGame();
            assertEquals(7, game.getSize());

            OceanMap omExpected1 = new OceanMap(7);
            omExpected1.setCoordinate(OceanMap.SHIP, 1, 0);
            omExpected1.setCoordinate(OceanMap.SHIP, 2, 0);
            omExpected1.setCoordinate(OceanMap.SHIP, 3, 0);
            omExpected1.setCoordinate(OceanMap.SHIP, 4, 0);
            omExpected1.setNumberOfShips(2);
            RadarMap rmExpected1 = new RadarMap(7);

            BattleShip s1 = new BattleShip(2);
            BattleShip s2 = new BattleShip(2);
            s1.translate(1, 0);
            s2.translate(3, 0);

            List<BattleShip> sExpected1 = new ArrayList<>();
            sExpected1.add(s1);
            sExpected1.add(s2);

            Player p1Expected = new Player("Alice", omExpected1, rmExpected1, sExpected1);

            OceanMap omExpected2 = new OceanMap(7);
            omExpected2.setCoordinate(OceanMap.SHIP, 2, 1);
            omExpected2.setCoordinate(OceanMap.SHIP, 3, 1);
            omExpected2.setCoordinate(OceanMap.SHIP, 3, 2);
            omExpected2.setCoordinate(OceanMap.SHIP, 3, 3);
            omExpected2.setNumberOfShips(2);
            RadarMap rmExpected2 = new RadarMap(7);

            BattleShip s3 = new BattleShip(2);
            BattleShip s4 = new BattleShip(2);
            s3.translate(2, 1);
            s4.rotate();
            s4.translate(3, 2);

            List<BattleShip> sExpected2 = new ArrayList<>();
            sExpected2.add(s3);
            sExpected2.add(s4);

            Player p2Expected = new Player("Bob", omExpected2, rmExpected2, sExpected2);

            assertPlayerEquals(p1Expected, game.getPlayer1());
            assertPlayerEquals(p2Expected, game.getPlayer2());


        } catch (IOException e) {
            fail("This file should exist... Something is off. oopsie!");
        }
    }
}
