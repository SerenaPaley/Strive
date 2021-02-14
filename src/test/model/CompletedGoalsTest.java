package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CompletedGoalsTest {

    private CompletedGoals testCompletedGoals;
    Goal test1;
    Goal test2;
    Goal test3;
    Goal test4;



    @BeforeEach
    void setup() {

        testCompletedGoals = new CompletedGoals("myCompleted");
        test1 = new Goal("go to sleep early", Goal.TimeFrame.DAILY, 5);
        test2 = new Goal("eat healthy", Goal.TimeFrame.WEEKLY, 9);
        test3 = new Goal("do a puzzle", Goal.TimeFrame.MONTHLY, 35);
        test4 = new Goal("learn to cook", Goal.TimeFrame.YEARLY, 15);


    }


    @Test
    void testConstructorCompletedGoals() {
        testCompletedGoals = new CompletedGoals("myCompleted");
        assertEquals("myCompleted", testCompletedGoals.getName());
        assertEquals(0, testCompletedGoals.getNumGoals());

    }

    @Test
    void testAddGoal() {
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test1);
        assertEquals(1, testCompletedGoals.getNumGoals());
    }

    @Test
    void testRemoveGoal() {
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test2);
        testCompletedGoals.addGoal(test3);
        testCompletedGoals.addGoal(test4);
        assertEquals(3, testCompletedGoals.getNumGoals());
        testCompletedGoals.removeGoal(2);
        assertEquals(2, testCompletedGoals.getNumGoals());
    }

    @Test
    void testUpdateGoal() {
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test2);
        testCompletedGoals.addGoal(test3);
        testCompletedGoals.addGoal(test4);
        assertEquals(3, testCompletedGoals.getNumGoals());
        testCompletedGoals.updateGoal(3, test4);
        assertEquals(3, testCompletedGoals.getNumGoals());
        //test update
    }

    @Test
    void testGoalSize() {
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test2);
        assertEquals(1,testCompletedGoals.getGoalListSize());
    }

    @Test
    void testGoalComplete() {
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test1);
        testCompletedGoals.addGoal(test2);
        assertEquals(2,testCompletedGoals.getGoalListSize());
        testCompletedGoals.addGoalComplete(2);
        assertEquals(1, testCompletedGoals.completedList.size());
    }

    @Test
    void testPrintGoals() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        //add goals to agenda
        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test1);
        testCompletedGoals.addGoal(test2);
        testCompletedGoals.addGoal(test3);
        assertEquals(3,testCompletedGoals.getGoalListSize());

        testCompletedGoals.addGoalComplete(2);
        testCompletedGoals.removeGoal(2);
        testCompletedGoals.addGoalComplete(2);
        testCompletedGoals.removeGoal(2);
        assertEquals(1, testCompletedGoals.getNumGoals());
        //check to see if printGoals() is in the output log
        testCompletedGoals.printGoals();
        assertEquals("DAILY\n" + "Goal 1: " + test1.getName() + "\n" + "WEEKLY\n" + "MONTHLY\n" + "YEARLY\n" + "COMPLETED\n" + "Finished 1: " + test2.getName() + " " + test2.getNumStars() +  " stars\n" + "Finished 2: " + test3.getName() + " " + test3.getNumStars() +  " stars\n", output.toString());
    }

    @Test
    void testPrintCategory() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(0, testCompletedGoals.getNumGoals());
        testCompletedGoals.addGoal(test1);
        assertEquals(1,testCompletedGoals.getGoalListSize());

        testCompletedGoals.printCategory(test1.getTimeFrame());

        assertEquals("DAILY\n" + "Goal 1: " + test1.getName() + "\n" , output.toString());
    }


}
