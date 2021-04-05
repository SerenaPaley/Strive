package model;

import exceptions.NegativeStarsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    private Goal testGoal;

    @BeforeEach
    void setUp() throws NegativeStarsException {
        testGoal = new Goal ("Wake up early", Goal.TimeFrame.DAILY, 5);
    }

    @Test
    void testConstructorNoExceptionThrown(){
        try {
            testGoal = new Goal ("Wake up early", Goal.TimeFrame.DAILY, 5);
            assertEquals("Wake up early", testGoal.getName());
            assertEquals(Goal.TimeFrame.DAILY, testGoal.getTimeFrame());
            assertEquals(5, testGoal.getNumStars());
        } catch (NegativeStarsException e) {
            fail("caught exception");
        }
    }

    @Test
    void testConstructorExceptionThrown(){
        try {
            testGoal = new Goal ("Wake up early", Goal.TimeFrame.DAILY, -1);
            assertEquals("Wake up early", testGoal.getName());
            assertEquals(Goal.TimeFrame.DAILY, testGoal.getTimeFrame());
            assertEquals(5, testGoal.getNumStars());
        } catch (NegativeStarsException e) {
            //should catch this exception
        }
    }

    @Test
    void testGetName(){
        assertEquals("Wake up early", testGoal.getName());
        assertEquals(Goal.TimeFrame.DAILY, testGoal.getTimeFrame());
        assertEquals(5, testGoal.getNumStars());
    }
}
