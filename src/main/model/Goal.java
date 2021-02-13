package model;

public class Goal {

    private String name;   // the user's goal
    private int numStars;  // the number of stars for a goal
    private boolean completed; //true if a goal is completed, false otherwise

    public enum TimeFrame { //public!!!
        DAILY, WEEKLY, MONTHLY, YEARLY;
    }

    private TimeFrame timeFrame;

    //EFFECTS: creates a new goal. including goal itself, timeframe and number of stars
    public Goal(String goal, TimeFrame goalTimeFrame, int numStars) {
        this.name = goal;
        this.timeFrame = goalTimeFrame;
        this.numStars = numStars;

    }

    // EFFECTS: returns name of goal
    public String getName() {
        return name;
    }



}
