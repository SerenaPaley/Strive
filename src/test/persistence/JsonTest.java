package persistence;

import model.Agenda;
import model.Goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
// CITATION: modeled from the JsonSerializatioinDemo
public class JsonTest {
    protected void checkGoal(String name, Goal.TimeFrame timeframe, int numStars, Goal goal) {
        assertEquals(name, goal.getName());
        assertEquals(timeframe, goal.getTimeFrame());
        assertEquals(numStars, goal.getNumStars());
    }

}
