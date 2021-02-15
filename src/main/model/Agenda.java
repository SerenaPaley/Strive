package model;

import java.util.ArrayList;

// Represents an agenda to hold a list of goals and a list of completed goals with a name

public class Agenda {
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
        completedList.add(goalList.get(goalDone - 1));
    }

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
}