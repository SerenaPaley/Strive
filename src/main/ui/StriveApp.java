package ui;

import model.Agenda;
import model.Goal;

import java.util.Scanner;

public class StriveApp {

    //private String sayGoal;
   // private char timeFrame;
    //private int numStars;
    private Agenda myAgenda;
    private Scanner userChoice;


    public StriveApp() {
        runStrive();
    }


    // MODIFIES: this
    // EFFECTS: managing user input
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
    // EFFECTS: initialized agendas
    private void initialize() {
        myAgenda = new Agenda("Serena's Goals");
        userChoice = new Scanner(System.in);
        Goal test1 = new Goal("test1", Goal.TimeFrame.DAILY, 5);
        Goal test2 = new Goal("test2", Goal.TimeFrame.WEEKLY, 9);
        Goal test3 = new Goal("test3", Goal.TimeFrame.MONTHLY, 35);
        Goal test4 = new Goal("test4", Goal.TimeFrame.YEARLY, 15);
        myAgenda.addGoal(test1);
        myAgenda.addGoal(test2);
        myAgenda.addGoal(test3);
        myAgenda.addGoal(test4);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void showOptions() {
        System.out.println("\n" + "Let's have a great day!");
        System.out.println("Please choose from");
        System.out.println("(A)dd new goal");
        System.out.println("(R)emove goal");
        System.out.println("(U)pdate goal");
        System.out.println("(P)rogress");
        System.out.println("(Q)uit");
    }

    private void timeFrameOptions() {
        System.out.println("(D)aily");
        System.out.println("(W)eekly");
        System.out.println("(M)onthly");
        System.out.println("(Y)early");
    }



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
        }
    }


    // MODIFIES: this
    // EFFECTS: gets a new goal
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
        //userChoice.nextLine();

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
        myAgenda.printGoals();
        System.out.println("Which goal number do you want to remove?");
        int removePos = userChoice.nextInt();
        userChoice.nextLine();
        myAgenda.removeGoal(removePos);

    }

    // REQUIRES:
    // MODIFIES:this
    // EFFECTS:
    private void goUpdate() {
        myAgenda.printGoals();
        System.out.println("Which goal do you want to update?");
        int updatePos = userChoice.nextInt();
        userChoice.nextLine();
        myAgenda.updateGoal(updatePos, goalInput());
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void listGoals() {
        myAgenda.printGoals();
    }






}




