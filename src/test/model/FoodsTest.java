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

    private Foods savedFoods;
    private Foods currentDaysFood;


    @BeforeEach
    void runBefore() {
        chips = new Food(5, 5, 5, "chips", 10, "grams");
        chicken = new Food(5, 5, 5, "chips", 10, "grams");
        chocolate = new Food(5, 5, 5, "chips", 10, "grams");
        popcorn = new Food(5, 5, 5, "chips", 10, "grams");
        savedFoods = new Foods();
        currentDaysFood = new Foods();


    }

    @Test
    void testSearchFoodIfFoodIsNotPresent() {


        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        assertFalse(savedFoods.searchFood("red"));


    }

    @Test
    void testSearchFoodIfFoodIsPresent() {
        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        assertTrue(model.Foods.searchFood("chips", savedFoods));

    }

    @Test
        // Food is not in saved

    void testaddFoodFoodToCurrentNotSaved() {
        savedFoods.addFood(chips);
        assertFalse(model.Foods.addFoodFoodToCurrent("chocolate", 50, currentDaysFood, savedFoods));

    }

    @Test
        // Food is in saved
    void testaddFoodFoodToCurrentSaved() {
        savedFoods.addFood(chips);
        currentDaysFood.addFood(chicken);
        assertTrue(model.Foods.addFoodFoodToCurrent("chips", 50, currentDaysFood, savedFoods));
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
        savedFoods.addFood(chips);
        savedFoods.addFood(chocolate);
        assertTrue(savedFoods.getProteinFromFood() == 10);
    }

    @Test
    void testTotalCarbs() {
        chips.setConsumed(10);
        chocolate.setConsumed(10);
        savedFoods.addFood(chips);
        savedFoods.addFood(chocolate);
        assertTrue(savedFoods.getCarbFromFood() == 10);
    }

    @Test
    void testTotalFat() {
        chips.setConsumed(10);
        chicken.setConsumed(10);
        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        assertTrue(savedFoods.getFatFromFood() == 10);
    }

    @Test
    void testaddFoodFood() {
        savedFoods.addFood(chicken);
        assertEquals(savedFoods.get(0), chicken);
    }

    @Test
    void testTotalCalFromFood() {
        chicken.setConsumed(10);
        chips.setConsumed(10);
        currentDaysFood.addFood(chips);
        currentDaysFood.addFood(chicken);
        assertTrue(currentDaysFood.totalCalFromFood() == 170);
    }

    @Test
    void addFoodFoodToSavedNotPresent() {
        savedFoods.addFood(chips);
        assertTrue(model.Foods.addFoodFoodToSaved(5, 5, 5, "ice cream", 5,
                "mL", savedFoods));
    }

    @Test
    void addFoodFoodToSavedPresent() {
        savedFoods.addFood(chips);
        assertFalse(model.Foods.addFoodFoodToSaved(5, 5, 5, "chips", 5,
                "mL", savedFoods));
    }
}


