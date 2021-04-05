package persistence;

import model.Agenda;
import model.Goal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// Represents a JSON reader for data persistence in Agenda
// CITATION: modeled from the JsonSerializatioinDemo
public class JsonReader {
    private String source;

    // EFFECTS: creates a reader to read from a file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads Agenda from file and return it.
    // Throw IOException if an error occurs while reading the file
    public Agenda read() throws IOException{
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return checkAgenda(jsonObject);
    }

    // EFFECTS: reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: looks through Agenda in JSON object and returns the agenda
    private Agenda checkAgenda(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Agenda agenda = new Agenda(name);
        addGoals(agenda, jsonObject);
        return agenda;
    }

    // MODIFIES: agenda
    // EFFECTS: checks the goals in JSON object and adds them to the agenda
    private void addGoals(Agenda agenda, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("goals");
        for (Object json : jsonArray) {
            JSONObject nextGoal = (JSONObject) json;
            addGoal(agenda, nextGoal);
        }
    }

    // MODIFIES: agenda
    // EFFECTS: checks goal in JSON object and adds them to the agenda
    private void addGoal(Agenda agenda, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Goal.TimeFrame timeframe = Goal.TimeFrame.valueOf(jsonObject.getString("timeframe"));
        int numStars = jsonObject.getInt("number of stars");
        Goal goal = new Goal(name, timeframe, numStars);
        if (timeframe == Goal.TimeFrame.COMPLETED) {
            agenda.addGoalComplete(goal);
        } else {
            agenda.addGoal(goal);
        }
    }

}
