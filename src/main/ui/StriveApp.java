package ui;

import model.Agenda;
import model.Goal;

import java.util.ArrayList;
import java.util.Scanner;

// Console based user interface for Strive
public class StriveApp {

    private Agenda myAgenda;
    private Scanner userChoice;

    // EFFECTS: runs the Strive application
    public StriveApp() {
        runStrive();
    }

    // MODIFIES: this
    // EFFECTS: manages user input
    private void runStrive() {
        boolean quit = false;
        String command = null;

        initialize();

        while (!quit) {
            showOptions();

            command = userChoice.next();
            userChoice.nextLine();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                quit = true;
            } else {
                completeTask(command);
            }
        } //while

        System.out.println("Great work - See you tomorrow!");
    }

    // MODIFIES: this
    // EFFECTS: initialized agenda
    private void initialize() {
        myAgenda = new Agenda("Serena's Goals");
        userChoice = new Scanner(System.in);
    }

    // EFFECTS: prints user options
    private void showOptions() {
        System.out.println("\n" + "Let's have a great day!");
        System.out.println("Please choose from");
        System.out.println("(A)dd new goal");
        System.out.println("(R)emove goal");
        System.out.println("(U)pdate goal");
        System.out.println("(P)rogress");
        System.out.println("(C)heck off");
        System.out.println("(Q)uit");
    }

    // EFFECTS: prints timeframe options
    private void timeFrameOptions() {
        System.out.println("(D)aily");
        System.out.println("(W)eekly");
        System.out.println("(M)onthly");
        System.out.println("(Y)early");
    }

    // MODIFIES: this
    // EFFECTS: directs next function based on user input
    private void completeTask(String command) {
        if (command.equals("A")) {
            goAdd();
        } else if (command.equals("R")) {
            goRemove();
        } else if (command.equals("U")) {
            goUpdate();
        } else if (command.equals("P")) {
            listGoals();
        } else if (command.equals("C")) {
            checkOffGoal();
        }
    }

    // MODIFIES: this
    // EFFECTS: gets a new goal and required input
    private Goal goalInput() {
        System.out.println("Enter new goal:");
        String chooseGoal = userChoice.nextLine();
        Goal.TimeFrame timeFrameEnum = Goal.TimeFrame.DAILY;

        System.out.println("When will this be completed?");
        timeFrameOptions();
        String timeFrame = userChoice.next();
        timeFrame = timeFrame.toUpperCase();
        //validate
        while (!(timeFrame.equals("D") || timeFrame.equals("W") ||  timeFrame.equals("M") || timeFrame.equals("Y"))) {
            System.out.println("Invalid time frame");
            timeFrameOptions();
            timeFrame = userChoice.next();
            timeFrame = timeFrame.toUpperCase();
        }

        switch (timeFrame) {
            case "D": timeFrameEnum = Goal.TimeFrame.valueOf("DAILY");
            case "W": timeFrameEnum = Goal.TimeFrame.valueOf("WEEKLY");
            case "M": timeFrameEnum = Goal.TimeFrame.valueOf("MONTHLY");
            case "Y": timeFrameEnum = Goal.TimeFrame.valueOf("YEARLY");
        }

        //stars
        System.out.println("How many stars is this goal?");
        int numStars = userChoice.nextInt();
        //create new goal
        Goal myGoal = new Goal(chooseGoal, timeFrameEnum, numStars);
        return myGoal;
    }

    // MODIFIES: this
    // EFFECTS: adds a new goal to user's agenda
    private void goAdd() {
        myAgenda.addGoal(goalInput());
    }

    // MODIFIES:this
    // EFFECTS: removes goal from list based on number assigned when printed
    private void goRemove() {
        printGoals();
        System.out.println("Which goal number do you want to remove?");
        int removePos = userChoice.nextInt();
        userChoice.nextLine();
        myAgenda.removeGoal(removePos);
    }

    // REQUIRES: >= 1 goal in agenda
    // MODIFIES:this
    // EFFECTS: update elements of existing goal
    private void goUpdate() {
        printGoals();
        System.out.println("Which goal do you want to update?");
        int updatePos = userChoice.nextInt();
        userChoice.nextLine();
        myAgenda.updateGoal(updatePos, goalInput());
    }

    // EFFECTS: prints current goals
    private void listGoals() {
        printGoals();
    }

    // REQUIRES: >= 1 goal in agenda
    // MODIFIES:this
    // EFFECTS: marks a goal as complete and moves it to completed list
    private void checkOffGoal() {
        printGoals();
        System.out.println("Which goal is completed?");
        int finGoal = userChoice.nextInt();
        userChoice.nextLine();
        myAgenda.addGoalComplete(finGoal);
        myAgenda.removeGoal(finGoal);
    }

    // MODIFIES: this
    // EFFECTS: numerically list current goals into TimeFrame categories
    private void printGoals() {
        printCategory(Goal.TimeFrame.DAILY);
        printCategory(Goal.TimeFrame.WEEKLY);
        printCategory(Goal.TimeFrame.MONTHLY);
        printCategory(Goal.TimeFrame.YEARLY);

        ArrayList<Goal> goalList = myAgenda.getGoalList();
        ArrayList<Goal> completedGoalList = myAgenda.getGoalListCompleted();

        //get completed goal
        System.out.println("COMPLETED");
        for (int i = 0; i < completedGoalList.size(); i++) {
            System.out.println("Finished " + (i + 1) + ": " + completedGoalList.get(i).getName() + " "
                    + completedGoalList.get(i).getNumStars() + " stars");
        } //for
    }

    // MODIFIES: this
    // EFFECTS: numerically list current goals in one timeframe category
    private void printCategory(Goal.TimeFrame goalType) {
        ArrayList<Goal> goalList = myAgenda.getGoalList();

        System.out.println(goalType);
        for (int i = 0; i < goalList.size(); i++) {
            if (goalList.get(i).getTimeFrame() == goalType) {
                System.out.println("Goal " + (i + 1) + ": " + goalList.get(i).getName());
            }
        } //for
    }
}




