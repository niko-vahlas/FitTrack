package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.FitnessApp;

import java.util.ArrayList;
import java.util.List;

// represents a list of foods
public class Foods implements Writable {
    private List<Food> foods;

    public Foods() {
        this.foods = new ArrayList<>();
    }

    // EFFECTS: returns true if there is a food name that matches the string, returns false otherwise
    public boolean searchFood(String s) {
        for (Food f : foods) {
            if (f.getName().equals(s)) {
                return true;
            }

        }
        return false;
    }

    // EFFECTS: returns true if there is a food name that matches the string, returns false otherwise
    public void removeFood(String s) {
        for (Food f : foods) {
            if (f.getName().equals(s)) {
                foods.remove(f);
            }

        }
    }

    /*
    EFFECTS: returns a copy of a food from a list of foods
    REQUIRES: a food name that is present in a list of foods
    MODIFIES: this
     */
    public Food takeFoodFromSaved(String s) {
        int length = foods.size();
        for (int i = 0; i < length; i++) {
            Food currentSavedFood = foods.get(i);
            String nameOfFood = currentSavedFood.getName();
            if (nameOfFood.equals(s)) {
                Food newFood = Food.eatFood(currentSavedFood);
                return newFood;
            }
        }
        return new Food(5, 5, 5, "Red", 5, 5,  "red");
    }

    /*
     REQUIRES: all floats must be whole numbers.
     MODIFIES: This
     EFFECTS: If the string not the name a food in a list, a new food with that name is added.
     */
    public boolean addFoodToSaved(float fat, float carb, float protein, String name, float serving, String unit) {
        if (!searchFood(name)) {
            addFood(new Food(fat, carb, protein, name, serving, 0, unit));
            return true;
        }
        return false;
    }

    // EFFECTS: returns total calories in a list of foods in form of a float.
    public float totalCalFromFood() {
        float kcal = 0;
        for (Food f : foods) {
            kcal += f.calculateCalories();
        }
        return kcal;
    }

    // EFFECTS: returns total protein in a list of foods in form of a float.
    public float getProteinFromFood() {
        float totalProtein = 0;
        for (Food f : foods) {
            totalProtein += f.calculateProtein();
        }

        return totalProtein;
    }

    // EFFECTS: returns total fat in a list of foods in form of a float.
    public float getFatFromFood() {
        float totalFat = 0;
        for (Food f : foods) {
            totalFat += f.calculateFat();
        }

        return totalFat;
    }

    // EFFECTS: returns total carbs in a list of foods in form of a float.
    public float getCarbFromFood() {
        float totalCarb = 0;
        for (Food f : foods) {
            totalCarb += f.calculateCarbs();
        }

        return totalCarb;
    }

    // EFFECTS: adds food to list.
    public void addFood(Food f) {
        this.foods.add(f);
    }

    // EFFECTS: returns size of food list
    public int numFood() {
        return foods.size();
    }

    // EFFECTS: returns list of foods
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Foods", listsToJson());
        return json;
    }

    // EFFECTS: returns things in this food list as a JSON array
    private JSONArray listsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foods) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


}
