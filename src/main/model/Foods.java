package model;

import java.util.ArrayList;
import java.util.List;


public class Foods {

    // EFFECTS: returns true if there is a food name that matches the string, returns false otherwise
    public static boolean searchFood(String s, List<Food> saved) {
        for (Food f : saved) {
            if (f.getName().equals(s)) {
                return true;
            }

        }
        return false;
    }

    /*
    EFFECTS: returns true if the string matches the name of a food in a list of food, and
    then adds the consumed amount of food to a different list.
    returns false if string does not match.
    REQUIRES: float greater than 0
    MODIFIES: this and modifies food object
     */
    public static boolean addFoodToCurrent(String s, float consumed, List<Food> currentDayFood, List<Food> savedFood) {
        int length = savedFood.size();
        for (int i = 0; i < length; i++) {
            Food currentSavedFood = savedFood.get(i);
            String nameOfFood = currentSavedFood.getName();
            if (nameOfFood.equals(s)) {
                Food newFood = Food.eatFood(currentSavedFood);
                newFood.setConsumed(consumed);
                currentDayFood.add(newFood);

                return true;
            }
        }
        return false;
    }

    /*
     REQUIRES: all floats must be positive.
     MODIFIES: This
     EFFECTS: If the string not the name a food in a list, a new food with that name is added.
     */
    public static boolean addFoodToSaved(float fat, float carb, float protein, String name, float serving, String unit,
                                         List<Food> saved) {
        if (!searchFood(name, saved)) {
            addFood(new Food(fat, carb, protein, name, serving, unit), saved);
            return true;
        }
        return false;
    }

    // EFFECTS: returns total calories in a list of foods in form of a float.
    public static float totalCalFromFood(List<Food> foods) {
        float kcal = 0;
        for (Food f : foods) {
            kcal += f.calculateCalories();
        }
        return kcal;
    }

    // EFFECTS: returns total protein in a list of foods in form of a float.
    public static float getProteinFromFood(List<Food> foods) {
        float totalProtein = 0;
        for (Food f : foods) {
            totalProtein += f.calculateProtein();
        }

        return totalProtein;
    }

    // EFFECTS: returns total fat in a list of foods in form of a float.
    public static float getFatFromFood(List<Food> foods) {
        float totalFat = 0;
        for (Food f : foods) {
            totalFat += f.calculateFat();
        }

        return totalFat;
    }

    // EFFECTS: returns total carbs in a list of foods in form of a float.
    public static float getCarbFromFood(List<Food> foods) {
        float totalCarb = 0;
        for (Food f : foods) {
            totalCarb += f.calculateCarbs();
        }

        return totalCarb;
    }

    // EFFECTS: adds food to list.
    public static void addFood(Food f, List<Food> foodList) {
        foodList.add(f);
    }


}
