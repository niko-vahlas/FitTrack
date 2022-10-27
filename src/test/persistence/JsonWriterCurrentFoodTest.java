package persistence;

import model.Food;
import model.Foods;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterCurrentFoodTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Foods foods = new Foods();
            JsonWriterCurrentFood writer = new JsonWriterCurrentFood("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Foods foods = new Foods();
            JsonWriterCurrentFood writer = new JsonWriterCurrentFood("./data/testWriterEmptyWorkroom1.json");
            writer.open();
            writer.writeCurrentFoods(foods);
            writer.close();

            JsonReaderCurrentFood reader = new JsonReaderCurrentFood("./data/testWriterEmptyWorkroom1.json");
            foods = reader.read();
            assertEquals(0, foods.numFood());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Foods foods = new Foods();
            foods.addFood(new Food(20, 2, 3, "sugar cookie", 20, 5, "grams"));
            foods.addFood(new Food(10, 2, 3, "flour", 10, 4, "grams"));
            JsonWriterCurrentFood writer = new JsonWriterCurrentFood("./data/testWriterGeneralWorkroom1.json");
            writer.open();
            writer.writeCurrentFoods(foods);
            writer.close();

            JsonReaderCurrentFood reader = new JsonReaderCurrentFood("./data/testWriterGeneralWorkroom1.json");
            foods = reader.read();
            List<Food> newFoods = foods.getFoods();
            assertEquals(2, newFoods.size());
            checkFood(20, 2, 3, "sugar cookie", 20, 5, "grams", newFoods.get(0));
            checkFood(10, 2, 3, "flour", 10, 4, "grams", newFoods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}