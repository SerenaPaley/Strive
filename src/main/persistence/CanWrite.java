package persistence;

import org.json.JSONObject;

// Represents an interface to return JSON objects
// CITATION: modeled from the JsonSerializatioinDemo
public interface CanWrite {

    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
