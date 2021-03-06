package persistence;

import model.Agenda;
import model.Goal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//CITATION: tests modeled from JsonSerializatioinDemo
class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNoFileExists()  {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Agenda agenda = reader.read();
            fail("Should be IOException");
        } catch (IOException e) {
            //all good
        }
    }

    @Test
    void testReaderEmptyAgenda() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAgenda.json");
        try {
            Agenda agenda = reader.read();
            assertEquals("My agenda", agenda.getName());
            assertEquals(0, agenda.getGoalList().size());
            assertEquals(0, agenda.getGoalListCompleted().size());
        } catch (IOException e) {
            fail("File not read");
        }
    }

    @Test
    void testReadergeneralAgenda() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAgenda.json");
        try {
            Agenda agenda = reader.read();
            assertEquals("My agenda", agenda.getName());
            List<Goal> goals = agenda.getGoalList();
            assertEquals(2, agenda.getGoalList().size());
            checkGoal("Eat healthy", Goal.TimeFrame.DAILY, 3, goals.get(0));
            checkGoal("Sleep early", Goal.TimeFrame.WEEKLY, 5, goals.get(1));
        } catch (IOException e) {
            fail("File not read");
        }
    }

}


