package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// CLASS-LEVEL: represents a name and points won by a player
public class Score implements Writable {
    String name;
    int points;

    // EFFECTS: construct a score object with a name and points;
    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    //EFFECTS: creates a json object from a score object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("points", points);
        return json;
    }

    //EFFECTS: checks whether two score objects are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return getPoints() == score.getPoints() && getName().equals(score.getName());
    }

    //EFFECTS: generates hashcode for score object based on name and points
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPoints());
    }
}

