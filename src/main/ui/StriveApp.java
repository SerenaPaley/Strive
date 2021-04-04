package ui;

import exceptions.NegativeStarsException;
import model.Agenda;
import model.Goal;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;

// Console based user interface for Strive
// CITATION: methods using JSON are modeled from the JsonSerializatioinDemo
public class StriveApp {
    private static final String JSON_STORE = "./data/agenda.json";



    private Agenda myAgenda;
    private Scanner userChoice;
    JsonReader jsonReader;
    JsonWriter jsonWriter;
    private Goal inputGoal;

    public Agenda getMyAgenda() {
        return myAgenda;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public String getJsonStore() {
        return JSON_STORE;
    }

    public void setMyAgenda(Agenda myAgenda) {
        this.myAgenda = myAgenda;
    }

    // EFFECTS: runs the Strive application
    public StriveApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
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
        System.out.println("(S)ave to file");
        System.out.println("(L)oad from file");
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
        } else if (command.equals("S")) {
            saveAgenda();
        } else if (command.equals("L")) {
            loadAgenda();
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
        String timeFrame = userChoice.next().toUpperCase();
        //validate
        while (!(timeFrame.equals("D") || timeFrame.equals("W") ||  timeFrame.equals("M") || timeFrame.equals("Y"))) {
            timeFrameOptions();
            timeFrame = userChoice.next().toUpperCase();
        }

        switch (timeFrame) {
            case "D": timeFrameEnum = Goal.TimeFrame.valueOf("DAILY");
                break;
            case "W": timeFrameEnum = Goal.TimeFrame.valueOf("WEEKLY");
                break;
            case "M": timeFrameEnum = Goal.TimeFrame.valueOf("MONTHLY");
                break;
            case "Y": timeFrameEnum = Goal.TimeFrame.valueOf("YEARLY");
                break;
        }

        //stars
        System.out.println("How many stars is this goal?"); // must be a number >= 0
        int numStars = userChoice.nextInt();
        //create new goal
        try {
            inputGoal = new Goal(chooseGoal, timeFrameEnum, numStars);
        } catch (NegativeStarsException e) {
            System.out.println("caught the NegativeStarsException");
        }
        return inputGoal;
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


    // EFFECTS: saves the current agenda to a file
    private void saveAgenda() {
        try {
            jsonWriter.open();
            jsonWriter.write(myAgenda);
            jsonWriter.close();
            System.out.println("Saved! " + myAgenda.getName() + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: unable to write to file " + JSON_STORE);
        }

    }


    // MODIFIES: this
    // EFFECTS: loads agenda from file
    private void loadAgenda() {
        try {
            myAgenda = jsonReader.read();
            System.out.println("Successfully loaded " + myAgenda.getName() + " from " + JSON_STORE);
        } catch (IOException | NegativeStarsException e) {
            System.out.println("Error: unable to reade from file: " + JSON_STORE);
        }
    }


}





