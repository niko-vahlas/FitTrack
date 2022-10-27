package persistence;

import model.Food;
import model.Foods;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(float fat, float carb, float protein, String name, float serving, float consumed, String unit, Food food) {
        assertEquals(name, food.getName());
        assertEquals(fat, food.getFat());
        assertEquals(carb, food.getCarb());
        assertEquals(protein, food.getProtein());
        assertEquals(serving, food.getServing());
        assertEquals(consumed, food.getConsumed());
        assertEquals(unit, food.getUnit());
    }
}