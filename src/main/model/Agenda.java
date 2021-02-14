package model;

import java.util.ArrayList;

public class Agenda {
    int numGoals; // the number of goals the user has made so far
    private String name; // name of agenda

    ArrayList<Goal> goalList = new ArrayList<Goal>();
    ArrayList<Goal> completedList = new ArrayList<Goal>();



    // REQUIRES: agenda name length > 0
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
    public int getNumGoals() {
        return numGoals;
    }


    // EFFECTS: returns size of goalList
    public int getGoalListSize() {
        return goalList.size();
    }



    // MODIFIES: this
    // EFFECTS: adds a new goal to agenda and number of goals is updated
    public Goal addGoal(Goal newGoal) {
        goalList.add(newGoal);
        numGoals++;
        return newGoal;
    }

    // MODIFIES: this
    // EFFECTS: adds a new goal to agenda and number of goals is updated
    public void addGoalComplete(int goalDone) {
        completedList.add(goalList.get(goalDone - 1));

    }

    // REQUIRES: at least one goal in goalList
    // MODIFIES: this
    // EFFECTS: removes goal from agenda (based off number in printGoals) and number of goals is updated
    public void removeGoal(int removePos) {
        goalList.remove(removePos - 1);
        numGoals--;
    }

    // REQUIRES: at least one goal in goalList
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

        //get completed goal
        System.out.println("COMPLETED");
        for (int i = 0; i < completedList.size(); i++) {
            System.out.println("Finished " + (i + 1) + ": " + completedList.get(i).getName() + " "
                    + completedList.get(i).getNumStars() + " stars");
        } //for
    }

    // MODIFIES: this
    // EFFECTS: numerically list current goals
    public void printCategory(Goal.TimeFrame goalType) {
        System.out.println(goalType);
        for (int i = 0; i < goalList.size(); i++) {
            if (goalList.get(i).getTimeFrame() == goalType) {
                System.out.println("Goal " + (i + 1) + ": " + goalList.get(i).getName());
            }
        } //for
    }


}