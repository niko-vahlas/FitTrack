package ui;

import model.Foods;
import model.Food;
import persistence.JsonReaderCurrentFood;
import persistence.JsonReaderSavedFood;
import persistence.JsonWriterCurrentFood;
import persistence.JsonWriterSavedFood;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



// Fitness application
public class FitnessApp {

    private static final String JSON_STORE_SAVED_FOODS = "./data/savedfoods.json";
    private static final String JSON_STORE_CURRENT_FOODS = "./data/currentfoods.json";
    private Scanner input;
    Foods savedFoods;
    Foods currentDayFood;

    private JsonWriterCurrentFood jsonWriterCurrentFood;
    private JsonWriterSavedFood jsonWriterSavedFood;
    private JsonReaderCurrentFood jsonReaderCurrentFood;
    private JsonReaderSavedFood jsonReaderSavedFood;

    // EFFECTS: runs the teller application
    public FitnessApp() throws FileNotFoundException {
        jsonWriterCurrentFood = new JsonWriterCurrentFood(JSON_STORE_CURRENT_FOODS);
        jsonReaderCurrentFood = new JsonReaderCurrentFood(JSON_STORE_CURRENT_FOODS);
        jsonWriterSavedFood = new JsonWriterSavedFood(JSON_STORE_SAVED_FOODS);
        jsonReaderSavedFood = new JsonReaderSavedFood(JSON_STORE_SAVED_FOODS);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            displayNutrition();
        } else if (command.equals("w")) {
            addNewFoodForDay();
        } else if (command.equals("v")) {
            addNewFoodToList();
        } else if (command.equals("s")) {
            saveCurrentFoods();
            saveSavedFoods();
        } else if (command.equals("l")) {
            loadCurrentFoods();
            loadSavedFoods();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes food lists
    private void init() {
        savedFoods = new Foods();
        currentDayFood = new Foods();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> display nutrition information for the day");
        System.out.println("\tw -> add food");
        System.out.println("\tv -> add food to database");
        System.out.println("\ts -> save food data");
        System.out.println("\tl -> load food data");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: displays the nutrition information of a food list
    private void displayNutrition() {


        System.out.println("Calories: " + currentDayFood.totalCalFromFood());
        System.out.println("Protein: " + currentDayFood.getProteinFromFood());
        System.out.println("Fat: " + currentDayFood.getFatFromFood());
        System.out.println("Carb: " + currentDayFood.getCarbFromFood());
    }

    // MODIFIES: this
    // EFFECTS: adds a food to the days list
    private void addNewFoodForDay() {
        System.out.println("Enter food name");
        String foodName = input.next();
        System.out.println("Enter quantity consumed");
        float foodQuantity = input.nextFloat();


        if (foodQuantity < 0.0) {
            System.out.println("Cannot consume negative food...");

        } else if (savedFoods.searchFood(foodName)) {
            Food newFood = savedFoods.takeFoodFromSaved(foodName);
            newFood.setConsumed(foodQuantity);
            currentDayFood.addFood(newFood);

            System.out.println("Food has been added to your daily foods");

        } else {
            System.out.println("Food not found, please add to the main list first");
        }


    }

    // MODIFIES: this
    // EFFECTS: adds a new food to the list
    private void addNewFoodToList() {
        System.out.println("Enter grams of fat per serving:");
        float gramsFat = input.nextFloat();
        System.out.println("Enter grams of carbohydrates per serving:");
        float gramsCarb = input.nextFloat();
        System.out.println("Enter grams of protein per serving:");
        float gramsProtein = input.nextFloat();
        System.out.println("Enter name:");
        String name = input.next();
        System.out.println("Enter serving size:");
        float servingSize = input.nextFloat();
        System.out.println("Enter the unit:");
        String unit = input.next();

        if (savedFoods.addFoodToSaved(gramsFat, gramsCarb, gramsProtein, name, servingSize, unit)) {
            System.out.println("Food has been added to the main list and is available for selection.");
        } else {
            System.out.println("Food is already present in the list.");
        }


    }

    // EFFECTS: saves the saved foods to file
    private void saveSavedFoods() {
        try {
            jsonWriterSavedFood.open();
            jsonWriterSavedFood.writeSavedFoods(savedFoods);
            jsonWriterSavedFood.close();
            System.out.println("Saved saved foods to " + JSON_STORE_SAVED_FOODS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SAVED_FOODS);
        }
    }

    // EFFECTS: saves the current foods to file
    private void saveCurrentFoods() {
        try {
            jsonWriterCurrentFood.open();
            jsonWriterCurrentFood.writeCurrentFoods(currentDayFood);
            jsonWriterCurrentFood.close();
            System.out.println("Saved current foods to " + JSON_STORE_CURRENT_FOODS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CURRENT_FOODS);
        }
    }

    // EFFECTS: loads saved food list from file
    private void loadSavedFoods() {
        try {
            savedFoods = jsonReaderSavedFood.read();
            System.out.println("Loaded saved foods" + " from " + JSON_STORE_SAVED_FOODS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SAVED_FOODS);
        }
    }

    // EFFECTS: loads current food list from file
    private void loadCurrentFoods() {
        try {
            currentDayFood = jsonReaderCurrentFood.read();
            System.out.println("Loaded current day foods" + " from " + JSON_STORE_SAVED_FOODS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SAVED_FOODS);
        }
    }


}
