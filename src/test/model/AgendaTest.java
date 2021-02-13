package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda testAgenda;

    @BeforeEach
    void setup() { testAgenda = new Agenda("myAgenda");
    }

    @Test
    void testConstructor() {
        assertEquals("myAgenda",testAgenda.getName());
        assertEquals(0, testAgenda.getnumGoals());
    }

//    @Test
//    void testAddGoal() {
//        Goal testGoal = new Goal("go to sleep early", Goal.TimeFrame.DAILY, 10);
//        assertEquals(1, testAgenda.addGoal(testGoal));
//    }
}