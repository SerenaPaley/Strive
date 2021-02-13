package model;

import java.util.ArrayList;

public class Agenda {
    int numGoals; // the number of goals the user has made so far
    private String name; // name of agenda

    ArrayList<Goal> goalList = new ArrayList<Goal>();
    //ArrayList<Goal> completedList = new ArrayList<Goal>();


    // RE;QUIRES: agenda name length > 0
    // EFFECTS: creates a new agenda for goals to be stored in.
    public Agenda(String agendaName) {
        name = agendaName;
        numGoals = 0;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns number of goals
    public int getnumGoals() {
        return numGoals;
    }


    // MODIFIES: this
    // EFFECTS: adds a new goal to agenda and number of goals is updated
    public Goal addGoal(Goal newGoal) {
        goalList.add(newGoal);
        numGoals++;


        return newGoal;
    }

    //REQUIRES: at least one goal in goalList
    // MODIFIES: this
    // EFFECTS: removes goal from agenda (based off number in printGoals) and number of goals is updated
    public void removeGoal(int removePos) {
        for (int i = 0; i < goalList.size(); i++) {
            if ((i + 1) == removePos) {
                goalList.remove(i);
            }
        }
        numGoals--;
    }

    //REQUIRES: at least one goal in goalList
    // MODIFIES: this
    // EFFECTS: updates an existing goal
    public void updateGoal(int goalPos, Goal newGoal) {
        goalList.set((goalPos - 1), newGoal);
    }


    // MODIFIES: this
    // EFFECTS: numerically list current goals
    public void printGoals() {
        printCategory(Goal.TimeFrame.DAILY);
        printCategory(Goal.TimeFrame.WEEKLY);
        printCategory(Goal.TimeFrame.MONTHLY);
        printCategory(Goal.TimeFrame.YEARLY);
    }

    // MODIFIES: this
    // EFFECTS: numerically list current goals
    public void printCategory(Goal.TimeFrame goaltype) {
        System.out.println(goaltype);
        for (int i = 0; i < goalList.size(); i++) {
            if (goalList.get(i).getTimeFrame() == goaltype) {
                System.out.println("Goal " + (i + 1) + ": " + goalList.get(i).getName());
            }
        } //for


    }
}