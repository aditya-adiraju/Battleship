package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Game;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// CLASS-LEVEL COMMENT: Object to handle reading from a file
// RESOURCE USED: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String src;

    public JsonReader(String src) {
        this.src = src;
    }

    public Game readGame() throws IOException {
        String data = readFile(src);
        JSONObject json = new JSONObject(data);
        return parseGame(json);

    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    private Game parseGame(JSONObject jsonObject) {
        int size = jsonObject.getInt("size");
        JSONObject p1 = jsonObject.getJSONObject("player1");
        JSONObject p2 = jsonObject.getJSONObject("player2");

        Game g = new Game(size);
        Player player1 = parsePlayer(p1);
        Player player2 = parsePlayer(p2);
        g.setPlayer1(player1);
        g.setPlayer2(player2);

        return g;
    }

    private Player parsePlayer(JSONObject player) {
        String username = player.getString("username");
        OceanMap oceanMap = parseOceanMap(player.getJSONObject("oceanMap"));
        RadarMap radarMap = parseRadarMap(player.getJSONObject("radarMap"));
        List<BattleShip> ships = parseShips(player.getJSONArray("ships"));

        Player p = new Player(username, oceanMap, radarMap, ships);
        return p;
    }

    private List<BattleShip> parseShips(JSONArray ships) {
        List<BattleShip> battleShips = new ArrayList<>();
        for (Object ship : ships) {
            JSONObject nextShip = (JSONObject) ship;
            BattleShip b = parseBattleShip(nextShip);
            battleShips.add(b);
        }
        return battleShips;
    }

    private BattleShip parseBattleShip(JSONObject ship) {
        int size = ship.getInt("size");
        JSONArray coordinates = ship.getJSONArray("coordinates");
        BattleShip s = new BattleShip(size);
        List<Point> newCoordinates = new ArrayList<>();
        for (Object c : coordinates) {
            JSONObject nextC = (JSONObject) c;
            newCoordinates.add(parsePoint(nextC));
        }
        s.setCoordinates(newCoordinates);
        return s;
    }

    private Point parsePoint(JSONObject point) {
        int x = point.getInt("x");
        int y = point.getInt("y");
        return new Point(x, y);
    }

    private RadarMap parseRadarMap(JSONObject radarMap) {
        int size = radarMap.getInt("size");
        JSONArray board = radarMap.getJSONArray("board");
        RadarMap rm = new RadarMap(size);
        parseMapBoard(rm, board);
        return rm;
    }

    private OceanMap parseOceanMap(JSONObject oceanMap) {
        int size = oceanMap.getInt("size");
        JSONArray board = oceanMap.getJSONArray("board");
        int numberOfShips = oceanMap.getInt("numberOfShips");
        OceanMap om = new OceanMap(size);
        om.setNumberOfShips(numberOfShips);
        parseMapBoard(om, board);
        return om;
    }

    // RESOURCE: https://stackoverflow.com/a/57917460
    private void parseMapBoard(Map m, JSONArray board) {
        for (int i = 0; i < board.length(); i++) {
            JSONArray row = board.getJSONArray(i);
            for (int j = 0; j < row.length(); j++) {
                String coordinate = row.getString(j);
                m.setCoordinate(coordinate, j, i);
            }
        }
    }
}
