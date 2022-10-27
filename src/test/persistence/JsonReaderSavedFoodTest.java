package persistence;

import model.Food;
import model.Foods;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderSavedFoodTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderSavedFood reader = new JsonReaderSavedFood("./data/noSuchFile1.json");
        try {
            Foods fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReaderSavedFood reader = new JsonReaderSavedFood("./data/testReaderEmptyWorkroom1.json");
        try {
            Foods fd = reader.read();
            assertEquals(0, fd.numFood());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReaderSavedFood reader = new JsonReaderSavedFood("./data/testReaderGeneralWorkroom1.json");
        try {
            Foods foods = reader.read();
            List<Food> allFoods = foods.getFoods();
            assertEquals(2, allFoods.size());
            checkFood(20, 2, 3, "sugar cookie", 20, 5, "grams", allFoods.get(0));
            checkFood(10, 20, 30, "noodles", 10, 10, "grams", allFoods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}