package model;

import static model.Foods.*;

// Represents a specific food
public class Food {
    private float fat;                // The grams of fat per serving size
    private float carb;              // The grams of carbohydrates per serving size
    private float protein;            // The grams of protein per serving size
    private String name;        // The name of the food
    private float serving;     // The serving size

    private float consumed = 0; // Consumed

    private String unit;

    public Food(float fat, float carb, float protein, String name, float serving, String unit) {
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
        this.name = name;
        this.serving = serving;
        this.unit = unit;

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

    public float calculateCalories() {
        return this.consumed / serving * (protein * 4 + carb * 4 + fat * 9);
    }

    public float calculateProtein() {
        return this.consumed / serving * protein;
    }

    public float calculateCarbs() {
        return this.consumed / serving * carb;
    }

    public float calculateFat() {
        return this.consumed / serving * fat;
    }

    public static Food eatFood(Food savedFood) {
        return (new Food(savedFood.fat, savedFood.carb, savedFood.protein, savedFood.name, savedFood.serving,
                savedFood.unit));
    }


}
