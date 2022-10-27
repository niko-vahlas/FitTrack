package persistence;

import model.Food;
import model.Foods;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderCurrentFoodTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderCurrentFood reader = new JsonReaderCurrentFood("./data/noSuchFile.json");
        try {
            Foods fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReaderCurrentFood reader = new JsonReaderCurrentFood("./data/testReaderEmptyWorkRoom.json");
        try {
            Foods fd = reader.read();
            assertEquals(0, fd.numFood());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReaderCurrentFood reader = new JsonReaderCurrentFood("./data/testReaderGeneralWorkRoom.json");
        try {
            Foods foods = reader.read();
            List<Food> allFoods = foods.getFoods();
            assertEquals(2, allFoods.size());
            checkFood(5, 10, 15, "ice cream", 10, 10, "mL", allFoods.get(0));
            checkFood(10, 20, 30, "turkey", 10, 5, "grams", allFoods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}