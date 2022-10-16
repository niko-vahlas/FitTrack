package model;

import java.util.ArrayList;
import java.util.List;


public class Foods {
    public static void setup() {
        ArrayList<Food> savedFoods = new ArrayList<Food>();
        ArrayList<Food> currentDayFood = new ArrayList<>();

    }

    public static boolean searchFood(String s, List<Food> saved) {
        for (Food f : saved) {
            if (f.getName().equals(s)) {
                return true;
            }

        }
        return false;   // search for a food and return true if is there or false if it hasnt
    }

    // This class is responsible for adding a food to the current foods consumed
    // Checks if the food is indeed present in the saved foods.
    public static boolean addFoodToCurrent(String s, float consumed, List<Food> currentDayFood, List<Food> savedFood) {
        int length = savedFood.size();
        for (int i = 0; i < length; i++) {
            Food currentFood = savedFood.get(i);
            if (currentFood.getName() == s) {
                Food newFood = Food.eatFood(savedFood.get(i));
                newFood.setConsumed(consumed);
                addFood(newFood, currentDayFood);

                return true;
            }
        }
        return false;
    }

    public static boolean addFoodToSaved(float fat, float carb, float protein, String name, float serving, String unit,
                                         List<Food> saved) {
        if (!searchFood(name, saved)) {
            addFood(new Food(fat, carb, protein, name, serving, unit), saved);
            return true;
        }
        return false;
    }


    public static float totalCalFromFood(List<Food> foods) {
        float kcal = 0;
        for (Food f : foods) {
            kcal += f.calculateCalories();
        }
        return kcal;
    }

    public static float getProteinFromFood(List<Food> foods) {
        float protein = 0;
        for (Food f : foods) {
            protein += f.calculateProtein();
        }

        return protein;
    }

    public static float getFatFromFood(List<Food> foods) {
        float totalFat = 0;
        for (Food f : foods) {
            totalFat += f.calculateFat();
        }

        return totalFat;
    }

    public static float getCarbFromFood(List<Food> foods) {
        float totalCarb = 0;
        for (Food f : foods) {
            totalCarb += f.calculateCarbs();
        }

        return totalCarb;
    }

    // repeat for carb
    // repeat for fat
    // display the consumed foods


    public List<Food> getFood(List<Food> foodList) {
        return foodList;
    }

    public static void addFood(Food f, List<Food> foodList) {
        foodList.add(f);
    }

}
