package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.CanWrite;

import java.util.ArrayList;

// Represents an agenda to hold a list of goals and a list of completed goals with a name
// CITATION: methods using JSON are modeled from the JsonSerializatioinDemo

public class Agenda implements CanWrite {
    private String name; // name of agenda

    ArrayList<Goal> goalList = new ArrayList<Goal>();
    ArrayList<Goal> completedList = new ArrayList<Goal>();

    // REQUIRES: agenda name length > 0
    // EFFECTS: creates a new agenda for goals to be stored in.
    public Agenda(String agendaName) {
        name = agendaName;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns size of goalList
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    // EFFECTS: returns size of completedList
    public ArrayList<Goal> getGoalListCompleted() {
        return completedList;
    }

    // MODIFIES: this
    // EFFECTS: adds a new goal to agenda
    public Goal addGoal(Goal newGoal) {
        goalList.add(newGoal);
        return newGoal;
    }

    // MODIFIES: this
    // EFFECTS: adds a new goal to completed goal list
    public void addGoalComplete(int goalDone) {
        Goal goalToAdd = goalList.get(goalDone - 1);
        goalToAdd.setTimeFrame(Goal.TimeFrame.COMPLETED);
        completedList.add(goalToAdd);
    }

    // MODIFIES: this
    // EFFECTS: adds a goal to completed goal list
    public void addGoalComplete(Goal goal) {
        completedList.add(goal);
    }

    // REQUIRES: at least one goal in goalList
    // MODIFIES: this
    // EFFECTS: removes goal from agenda (based off number in printGoals)
    public void removeGoal(int removePos) {
        if (removePos > 0) {
            goalList.remove(removePos - 1);
        }
    }

    // REQUIRES: at least one goal in goalList
    // MODIFIES: this
    // EFFECTS: updates an existing goal
    public void updateGoal(int goalPos, Goal newGoal) {
        goalList.set((goalPos - 1), newGoal);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("goals", goalsToJson());
        return json;
    }

    // EFFECTS: returns goals in this agenda as a JSON array
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal g : goalList) {
            jsonArray.put(g.toJson());
        }
        for (Goal c : completedList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

}