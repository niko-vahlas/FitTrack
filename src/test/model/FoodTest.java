package model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.Food.eatFood;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    private Food testFood;


    @BeforeEach
    void runBefore() {
        testFood = new Food(5, 5, 5, "chips", 10, 0, "grams");
    }

    @Test
    void testConstructor() {
        assertEquals("chips", testFood.getName());
        assertEquals(5, testFood.getFat());
        assertEquals(5, testFood.getProtein());
        assertEquals(5, testFood.getCarb());
        assertEquals(10, testFood.getServing());
        assertEquals("grams", testFood.getUnit());
        assertEquals(0, testFood.getConsumed());
    }




    @Test
    void testCalculateCalories() {
        testFood.setConsumed(10);
        assertEquals(85, testFood.calculateCalories());
    }

    @Test
    void testCalculateProtein() {
        testFood.setConsumed(10);
        assertEquals(5, testFood.calculateProtein());
    }

    @Test
    void testCalculateCarbs() {
        testFood.setConsumed(10);
        assertEquals(5, testFood.calculateCarbs());
    }

    @Test
    void testCalculateFat() {
        testFood.setConsumed(10);
        assertEquals(5, testFood.calculateFat());
    }

    @Test
    void testSetCarb() {
        testFood.setCarb(10);
        assertTrue(testFood.getCarb() == 10);
    }

    @Test
    void testSetProtein() {
        testFood.setProtein(10);
        assertTrue(testFood.getProtein() == 10);
    }

    @Test
    void testSetFat() {
        testFood.setFat(10);
        assertTrue(testFood.getFat() == 10);
    }

    @Test
    void testSetUnit() {
        testFood.setUnit("mL");
        assertTrue(testFood.getUnit() == "mL");
    }

    @Test
    void testSetName() {
        testFood.setName("Josh");
        assertTrue(testFood.getName() == "Josh");
    }

    @Test
    void testSetServing() {
        testFood.setServing(20);
        assertTrue(testFood.getServing() == 20);
    }

    @Test
    void eatFoodTest() {
        Food newFood = eatFood(testFood);
        assertEquals(newFood.getName(), testFood.getName());
    }


}