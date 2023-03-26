package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// CLASS-LEVEL COMMENT: Object to handle writing to file and create persistence
// RESOURCE USED: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String dst;

    // EFFECTS: Constructs a writer object that writes to dst file
    public JsonWriter(String dst) {
        this.dst = dst;
    }

    // MODIFIES: this
    // EFFECTS: opens a PrintWriter that is ready to write to dst file.
    public void open() throws FileNotFoundException {
        File f = new File(dst);
        this.writer = new PrintWriter(f);
    }

    // MODIFIES: this
    // EFFECTS: save the given string to end of dst json file
    private void appendToFile(String jsonPayload) {
        writer.print(jsonPayload);
    }

    // MODIFIES: this
    // EFFECTS: add the given Writable payload to the end of a file
    public void write(Writable payload) {
        JSONObject json = payload.toJson();
        appendToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes PrintWriter object and its corresponding file stream.
    public void close() {
        this.writer.close();
    }

    // MODIFIES: this
    // EFFECTS: add the given JSON Array payload to the end of a file
    public void writeScoreList(JSONArray scoreListJson) {
        JSONObject json = new JSONObject();
        json.put("scores", scoreListJson);
        appendToFile(json.toString(TAB));
    }
}
