package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: return given object as a JSONObject
    JSONObject toJson();
}
