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
        chicken = new Food(5, 5, 5, "chicken", 10, 0,"grams");
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
void eatFoodTest() {
        savedFoods.addFood(chips);
        Food newFood = savedFoods.eatFood("chips", 10);
    Food noFood = savedFoods.eatFood("gg", 10);
        assertTrue(newFood.getConsumed() == 10);
        assertTrue(noFood.getConsumed() == 5);
}

@Test
void alreadyInDatabase() {
        savedFoods.addFood(chips);
        assertTrue(savedFoods.alreadyInDatabase("chips"));
    assertFalse(savedFoods.alreadyInDatabase("cps"));
}

    @Test
    void notInDatabase() {
        savedFoods.addFood(chips);
        assertTrue(savedFoods.notInDatabase("chips"));
        assertFalse(savedFoods.notInDatabase("cps"));
    }

    @Test
    void consumeFood() {
        savedFoods.consumeFood(chips);
        List<Food> foods = savedFoods.getFoods();
        Food newFood = foods.get(0);
        assertTrue(newFood.getName().equals("chips"));
    }

    @Test
    void removeFoodConsumedTest() {
        savedFoods.addFood(chips);
        savedFoods.addFood(chocolate);
        savedFoods.removeFoodConsumed("chips");
        assertTrue(savedFoods.getFoods().size() == 1);
        savedFoods.removeFoodConsumed("re");
        assertTrue(savedFoods.getFoods().size() == 1);

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

    @Test
    void removeFoodFromSavedPresent() {
        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        savedFoods.removeFood("chips");
        List<Food> foodsSaved = savedFoods.getFoods();
        assertTrue(foodsSaved.size() == 1);
    }

    @Test
    void removeFoodFromSavedNotPresent() {
        savedFoods.addFood(chips);
        savedFoods.addFood(chicken);
        savedFoods.removeFood("c");
        List<Food> foodsSaved = savedFoods.getFoods();
        assertTrue(foodsSaved.size() == 2);
    }
}




