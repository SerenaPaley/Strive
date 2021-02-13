package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoalTest {

    private Goal testGoal;

    @BeforeEach
    void setUp() {
        testGoal = new Goal ("Wake up early", Goal.TimeFrame.DAILY, 5);
    }

    @Test
    void testConstructor(){

    }
}
