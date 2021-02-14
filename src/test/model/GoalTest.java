package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {

    private Goal testGoal;

    @BeforeEach
    void setUp() {
        testGoal = new Goal ("Wake up early", Goal.TimeFrame.DAILY, 5);
    }

    @Test
    void testConstructor(){
        assertEquals("Wake up early", testGoal.getName());
        assertEquals(Goal.TimeFrame.DAILY, testGoal.getTimeFrame());
        assertEquals(5, testGoal.getNumStars());
    }

    @Test
    void testGetName(){
        assertEquals("Wake up early", testGoal.getName());
        assertEquals(Goal.TimeFrame.DAILY, testGoal.getTimeFrame());
        assertEquals(5, testGoal.getNumStars());
    }


}
