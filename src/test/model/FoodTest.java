package model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    private Food testFood;


    @BeforeEach
    void runBefore() {
        testFood = new Food(5, 5, 5, "chips", 10, "grams");
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


}