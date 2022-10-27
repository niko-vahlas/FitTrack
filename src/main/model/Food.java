package model;

import org.json.JSONObject;
import persistence.Writable;

import static model.Foods.*;

// Represents a specific food
public class Food implements Writable {
    private float fat;                  // The grams of fat per serving size
    private float carb;                 // The grams of carbohydrates per serving size
    private float protein;              // The grams of protein per serving size
    private String name;                // The name of the food
    private float serving;              // The serving size

    private float consumed = 0;         // Amount of food consumed

    private String unit;                // Unit of food



    /*
     * REQUIRES: all floats to be above 0.
     * EFFECTS: name of food, carbs per gram, protein per grams, serving and units are set.
     *
     *
     *
     */
    public Food(float fat, float carb, float protein, String name, float serving, float consumed, String unit) {
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
        this.name = name;
        this.serving = serving;
        this.unit = unit;
        this.consumed = consumed;

    }

    public float getFat() {
        return fat;
    }

    public float getCarb() {
        return carb;
    }

    public float getProtein() {
        return protein;
    }

    public String getName() {
        return name;
    }

    public float getServing() {
        return serving;
    }

    public String getUnit() {
        return unit;
    }

    public float getConsumed() {
        return consumed;
    }

    public void setCarb(float carb) {
        this.carb = carb;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServing(float serving) {
        this.serving = serving;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setConsumed(float consumed) {
        this.consumed = consumed;
    }

    // EFFECTS: returns a foods total calories.
    public float calculateCalories() {
        return this.consumed / serving * (protein * 4 + carb * 4 + fat * 9);
    }

    // EFFECTS: returns the grams of protein in a consumed food.
    public float calculateProtein() {
        return this.consumed / serving * protein;
    }

    // EFFECTS: returns the grams of carbs in a consumed food.
    public float calculateCarbs() {
        return this.consumed / serving * carb;
    }

    // EFFECTS: returns the grams of fat in a consumed food.
    public float calculateFat() {
        return this.consumed / serving * fat;
    }

    // EFFECTS: takes a food and returns a copy of said food.
    public static Food eatFood(Food savedFood) {
        return (new Food(savedFood.fat, savedFood.carb, savedFood.protein, savedFood.name, savedFood.serving,
                savedFood.consumed, savedFood.unit));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("fat", fat);
        json.put("carb", carb);
        json.put("protein", protein);
        json.put("serving", serving);
        json.put("unit", unit);
        json.put("consumed", consumed);
        return json;
    }


}
