package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda testAgenda;
    private CompletedGoals testCompletedGoals;
    Goal test1;
    Goal test2;
    Goal test3;
    Goal test4;


    @BeforeEach
    void setup() {
        testAgenda = new Agenda("myAgenda");
        testCompletedGoals = new CompletedGoals("myCompleted");
        test1 = new Goal("go to sleep early", Goal.TimeFrame.DAILY, 5);
        test2 = new Goal("eat healthy", Goal.TimeFrame.WEEKLY, 9);
        test3 = new Goal("do a puzzle", Goal.TimeFrame.MONTHLY, 35);
        test4 = new Goal("learn to cook", Goal.TimeFrame.YEARLY, 15);


    }

    @Test
    void testConstructor() {
        assertEquals("myAgenda",testAgenda.getName());
        assertEquals(0, testAgenda.getNumGoals());

        assertEquals(0, testCompletedGoals.getNumGoals());

    }

    @Test
    void testConstructorCompletedGoals() {
        assertEquals("myCompleted",testCompletedGoals.getName());
        assertEquals(0, testCompletedGoals.getNumGoals());
    }

    @Test
    void testAddGoal() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test1);
        assertEquals(1, testAgenda.getNumGoals());
    }

    @Test
    void testRemoveGoal() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test2);
        testAgenda.addGoal(test3);
        testAgenda.addGoal(test4);
        assertEquals(3, testAgenda.getNumGoals());
        testAgenda.removeGoal(2);
        assertEquals(2, testAgenda.getNumGoals());
    }

    @Test
    void testUpdateGoal() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test2);
        testAgenda.addGoal(test3);
        testAgenda.addGoal(test4);
        assertEquals(3, testAgenda.getNumGoals());
        testAgenda.updateGoal(3, test4);
        assertEquals(3, testAgenda.getNumGoals());
        //test update
    }

    @Test
    void testGoalSize() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test2);
        assertEquals(1,testAgenda.getGoalListSize());
    }

    @Test
    void testGoalComplete() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test1);
        testAgenda.addGoal(test2);
        assertEquals(2,testAgenda.getGoalListSize());
        testAgenda.addGoalComplete(2);
        assertEquals(1, testAgenda.completedList.size());
    }

    @Test
    void testPrintGoals() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        //add goals to agenda
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test1);
        testAgenda.addGoal(test2);
        assertEquals(2,testAgenda.getGoalListSize());

        testAgenda.addGoalComplete(2);
        testAgenda.removeGoal(2);
        assertEquals(1, testAgenda.getNumGoals());
        //check to see if printGoals() is in the output log
        testAgenda.printGoals();
        //assertEquals("DAILY\n" + "Goal 1: " + test1.getName() + "\n" + "WEEKLY\n" + "MONTHLY\n" + "YEARLY\n" + "COMPLETED\n" + "Finished 1: " + test2.getName() + " " + test2.getNumStars() +  " stars\n", output.toString());
    }

    @Test
    void testPrintCategory() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test1);
        assertEquals(1,testAgenda.getGoalListSize());

        testAgenda.printCategory(test1.getTimeFrame());

        assertEquals("DAILY\n" + "Goal 1: " + test1.getName() + "\n" , output.toString());
    }

}