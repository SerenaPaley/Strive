package model;

import exceptions.NegativeStarsException;
import org.json.JSONObject;
import persistence.CanWrite;

// Represents  a single goal with a name/description, timeframe, and number of stars
public class Goal implements CanWrite {

    private String name;   // the user's goal
    private int numStars;  // the number of stars for a goal

    // EFFECTS: an enumerated type for time frame. Either daily, weekly, monthly, or yearly
    public enum TimeFrame {
        DAILY, WEEKLY, MONTHLY, YEARLY, COMPLETED;
    }

    private TimeFrame timeFrame;

    // EFFECTS: creates a new goal. including description, timeframe and number of stars,
    // throws NegativeStarsException if numStars is <0
    public Goal(String goal, TimeFrame goalTimeFrame, int numStars) throws NegativeStarsException {
        if (numStars < 0) {
            throw new NegativeStarsException();
        }
        this.name = goal;
        this.timeFrame = goalTimeFrame;
        this.numStars = numStars;
    }

    // EFFECTS: returns name of goal
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns name of goal
    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    // EFFECTS: returns number of stars
    public int getNumStars() {
        return this.numStars;
    }

    // EFFECTS: sets the timeframe
    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("timeframe", timeFrame);
        json.put("number of stars", numStars);
        return json;
    }
}