package persistence;

import model.Agenda;
import model.Goal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//CITATION: tests modeled from JsonSerializatioinDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Agenda agenda = new Agenda("My agenda");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyWorkroom() {
        try {
            Agenda agenda = new Agenda("My agenda");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAgenda.json");
            writer.open();
            writer.write(agenda);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAgenda.json");
            agenda = reader.read();
            assertEquals("My agenda", agenda.getName());
            assertEquals(0, agenda.getGoalList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Agenda agenda = new Agenda("My agenda");
            agenda.addGoal(new Goal("Eat healthy", Goal.TimeFrame.DAILY,3));
            agenda.addGoal(new Goal("Sleep early", Goal.TimeFrame.WEEKLY,5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAgenda.json");
            writer.open();
            writer.write(agenda);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAgenda.json");
            agenda = reader.read();
            assertEquals("My agenda", agenda.getName());
            List<Goal> goals = agenda.getGoalList();
            assertEquals(2, agenda.getGoalList().size());
            checkGoal("Eat healthy", Goal.TimeFrame.DAILY, 3, goals.get(0));
            checkGoal("Sleep early", Goal.TimeFrame.WEEKLY, 5, goals.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
