package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodsTest {
    private Food chips;
    private Food chocolate;
    private Food popcorn;
    private Food chicken;

    private List<Food> savedFoods;
    private List<Food> currentDaysFood;


    @BeforeEach
    void runBefore() {
        chips = new Food(5, 5, 5, "chips", 10, "grams");
        chicken = new Food(5, 5, 5, "chips", 10, "grams");
        chocolate = new Food(5, 5, 5, "chips", 10, "grams");
        popcorn = new Food(5, 5, 5, "chips", 10, "grams");
        savedFoods = new ArrayList<>();
        currentDaysFood = new ArrayList<>();


    }

    @Test
    void testSearchFoodIfFoodIsNotPresent() {


        savedFoods.add(chips);
        savedFoods.add(chicken);
        assertFalse(model.Foods.searchFood("red", savedFoods));


    }

    @Test
    void testSearchFoodIfFoodIsPresent() {
        savedFoods.add(chips);
        savedFoods.add(chicken);
        assertTrue(model.Foods.searchFood("chips", savedFoods));

    }

    @Test
        // Food is not in saved

    void testAddFoodToCurrentNotSaved() {
        savedFoods.add(chips);
        assertFalse(model.Foods.addFoodToCurrent("chocolate", 50, currentDaysFood, savedFoods));

    }

    @Test
        // Food is in saved
    void testAddFoodToCurrentSaved() {
        savedFoods.add(chips);
        currentDaysFood.add(chicken);
        assertTrue(model.Foods.addFoodToCurrent("chips", 50, currentDaysFood, savedFoods));
        Food mostCurrent = currentDaysFood.get(1);
        assertEquals(mostCurrent.getConsumed(), 50);
        assertEquals(mostCurrent.getName(), "chips");
        Food mostCurrentSaved = savedFoods.get(0);
        assertEquals(mostCurrentSaved.getConsumed(), 0);
    }

    @Test
        //
    void testTotalProtein() {
        chips.setConsumed(10);
        chocolate.setConsumed(10);
        savedFoods.add(chips);
        savedFoods.add(chocolate);
        assertTrue(model.Foods.getProteinFromFood(savedFoods) == 10);
    }

    @Test
    void testTotalCarbs() {
        chips.setConsumed(10);
        chocolate.setConsumed(10);
        savedFoods.add(chips);
        savedFoods.add(chocolate);
        assertTrue(model.Foods.getCarbFromFood(savedFoods) == 10);
    }

    @Test
    void testTotalFat() {
        chips.setConsumed(10);
        chicken.setConsumed(10);
        savedFoods.add(chips);
        savedFoods.add(chicken);
        assertTrue(model.Foods.getFatFromFood(savedFoods) == 10);
    }

    @Test
    void testAddFood() {
        model.Foods.addFood(chicken, savedFoods);
        assertEquals(savedFoods.get(0), chicken);
    }


}
