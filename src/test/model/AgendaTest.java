package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda testAgenda;
    Goal test1;
    Goal test2;
    Goal test3;
    Goal test4;


    @BeforeEach
    void setup() {
        testAgenda = new Agenda("myAgenda");
        test1 = new Goal("go to sleep early", Goal.TimeFrame.DAILY, 5);
        test2 = new Goal("eat healthy", Goal.TimeFrame.WEEKLY, 9);
        test3 = new Goal("do a puzzle", Goal.TimeFrame.MONTHLY, 35);
        test4 = new Goal("learn to cook", Goal.TimeFrame.YEARLY, 15);


    }

    @Test
    void testConstructor() {
        assertEquals("myAgenda",testAgenda.getName());
        assertEquals(0, testAgenda.getNumGoals());

        assertEquals(0, testAgenda.getGoalListCompleted().size());

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
        assertEquals(1,testAgenda.getGoalList().size());
    }

    @Test
    void testGoalComplete() {
        assertEquals(0, testAgenda.getNumGoals());
        testAgenda.addGoal(test1);
        testAgenda.addGoal(test2);
        assertEquals(2,testAgenda.getGoalList().size());
        testAgenda.addGoalComplete(2);
        assertEquals(1, testAgenda.getGoalListCompleted().size());
    }
}