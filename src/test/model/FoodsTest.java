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
        chips = new Food(5, 5, 5, "chips", 10, 0, "grams");
        chicken = new Food(5, 5, 5, "chips", 10, 0,"grams");
        chocolate = new Food(5, 5, 5, "chips", 10, 0,"grams");
        popcorn = new Food(5, 5, 5, "chips", 10, 0,"grams");
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
    void testSearchFoodIfFoodPresent() {
        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        assertTrue(savedFoods.searchFood("chips"));


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
    void testTotalCalFromFood() {
        chicken.setConsumed(10);
        chips.setConsumed(10);
        currentDaysFood.addFood(chips);
        currentDaysFood.addFood(chicken);
        assertTrue(currentDaysFood.totalCalFromFood() == 170);
    }

    @Test
    void addFoodToSavedTestInList() {
        savedFoods.addFood(chips);
        assertFalse(savedFoods.addFoodToSaved(5, 5, 5, "chips", 5, "grams"));
        assertTrue(savedFoods.addFoodToSaved(5, 5, 5, "chocolate", 5, "grams"));
        List<Food> Foods = savedFoods.getFoods();
        Food AddedFood = Foods.get(1);
        String foodName = AddedFood.getName();
        assertTrue(foodName.equals( "chocolate"));
}

@Test
    void takeFoodFromSaved() {
        savedFoods.addFood(chips);
        Food newFood = savedFoods.takeFoodFromSaved("chips");
        String nameNewFood = newFood.getName();
        assertTrue("chips".equals(nameNewFood));
        Food newFoods = savedFoods.takeFoodFromSaved("blue");
        assertTrue(newFoods.getName().equals("Red"));


    }
}




