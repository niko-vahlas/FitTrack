package ui;


import model.Food;

import java.util.ArrayList;
import java.util.Scanner;


// Bank teller application
public class FitnessApp {
    private Scanner input;
    ArrayList<Food> savedFoods;
    ArrayList<Food> currentDayFood;

    // EFFECTS: runs the teller application
    public FitnessApp() {
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        savedFoods = new ArrayList<Food>();
        currentDayFood = new ArrayList<Food>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> display nutrition information for the day");
        System.out.println("\tw -> add food");
        System.out.println("\tv -> add food to database");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void displayNutrition() {


        System.out.println("Calories: " + model.Foods.totalCalFromFood(currentDayFood));
        System.out.println("Protein: " + model.Foods.getProteinFromFood(currentDayFood));
        System.out.println("Fat: " + model.Foods.getFatFromFood(currentDayFood));
        System.out.println("Carb: " + model.Foods.getCarbFromFood(currentDayFood));
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void addNewFoodForDay() {
        System.out.println("Enter food name");
        String foodName = input.next();
        System.out.println("Enter quantity consumed");
        float foodQuantity = input.nextFloat();


        if (foodQuantity < 0.0) {
            System.out.println("Cannot consume negative food...");

        } else if (model.Foods.addFoodToCurrent(foodName, foodQuantity, currentDayFood, savedFoods)) {
            System.out.println("Food has been added to your daily foods");

        } else {
            System.out.println("Food not found, please add to the main list first");
        }


    }

    // MODIFIES: this
    // EFFECTS: conducts a transfer transaction
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

        if (model.Foods.addFoodToSaved(gramsFat, gramsCarb, gramsProtein, name, servingSize, unit, savedFoods)) {
            System.out.println("Food has been added to the main list and is available for selection.");
        } else {
            System.out.println("Food is already present in the list.");
        }


    }


}
