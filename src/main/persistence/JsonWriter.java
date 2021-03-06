package persistence;

import model.Agenda;
import org.json.JSONObject;

//import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.*;

// Represents a writer that writes a JSON representation of Agenda to a file
// CITATION: modeled from the JsonSerializatioinDemo
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String endPoint;

    // EFFECTS:creates a writer to write to the desired file
    public JsonWriter(String endPoint) {
        this.endPoint = endPoint;
    }

    // MODIFIES: this
    // EFFECTS: tries to open file and throws FileNotFoundException if the file cannot be located
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(endPoint));
    }

    // MODIFIES: this
    // EFFECTS: writes a JSON version of Agenda
    public void write(Agenda agenda) {
        JSONObject json = agenda.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void saveToFile(String json) {
        writer.print(json);
    }


}
