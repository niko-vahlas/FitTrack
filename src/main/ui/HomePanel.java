package ui;

import persistence.JsonReaderCurrentFood;
import persistence.JsonReaderSavedFood;
import persistence.JsonWriterCurrentFood;
import persistence.JsonWriterSavedFood;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Creates a HomePanel
public class HomePanel extends JPanel {
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel middlePanel;
    private static final String saveFoodString = "Save Foods";
    private static final String loadFoodString = "Load Foods";
    private JButton saveButton;
    private JButton loadButton;
    private static final String JSON_STORE_SAVED_FOODS = "./data/savedfoods.json";
    private static final String JSON_STORE_CURRENT_FOODS = "./data/currentfoods.json";
    private JsonWriterCurrentFood jsonWriterCurrentFood;
    private JsonWriterSavedFood jsonWriterSavedFood;
    private JsonReaderCurrentFood jsonReaderCurrentFood;
    private JsonReaderSavedFood jsonReaderSavedFood;

    //EFFECTS: Constructor for homePanel
    public HomePanel() throws IOException {
        super(new BorderLayout());
        extracted();

        topPanel = new JPanel();
        JLabel label1 = new JLabel("Welcome to FitTrack");
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(label1);

        middlePanel = new JPanel();
        BufferedImage myPicture = ImageIO.read(new File("data/nutritionphoto.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);
        middlePanel.add(picLabel);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        saveButton = new JButton(saveFoodString);
        loadButton = new JButton(loadFoodString);
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        saveButton.addActionListener(new SaveListener());
        loadButton.addActionListener(new LoadListener());
    }

    // EFFECTS: Creates the JSON readers and writers (extracted method)
    private void extracted() {
        jsonWriterCurrentFood = new JsonWriterCurrentFood(JSON_STORE_CURRENT_FOODS);
        jsonReaderCurrentFood = new JsonReaderCurrentFood(JSON_STORE_CURRENT_FOODS);
        jsonWriterSavedFood = new JsonWriterSavedFood(JSON_STORE_SAVED_FOODS);
        jsonReaderSavedFood = new JsonReaderSavedFood(JSON_STORE_SAVED_FOODS);
    }

    // MODIFIES: this
    //EFFECTS: Listens for save button
    class SaveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: saves foods
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            saveSavedFoods();
            saveCurrentFoods();
        }
    }

    // MODIFIES: this
    // EFFECTS: Listens for load button
    class LoadListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: loads foods
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            loadSavedFoods();
            loadCurrentFoods();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the saved foods to file
    private void saveSavedFoods() {
        try {
            jsonWriterSavedFood.open();
            jsonWriterSavedFood.writeSavedFoods(Gui.savedFoods);
            jsonWriterSavedFood.close();
            System.out.println("Saved saved foods to " + JSON_STORE_SAVED_FOODS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SAVED_FOODS);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the current foods to file
    private void saveCurrentFoods() {
        try {
            jsonWriterCurrentFood.open();
            jsonWriterCurrentFood.writeCurrentFoods(Gui.currentDayFood);
            jsonWriterCurrentFood.close();
            System.out.println("Saved current foods to " + JSON_STORE_CURRENT_FOODS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CURRENT_FOODS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads saved food list from file

    private void loadSavedFoods() {
        try {
            Gui.savedFoods = jsonReaderSavedFood.read();
            System.out.println("Loaded saved foods" + " from " + JSON_STORE_SAVED_FOODS);
            FoodDatabasePanel.setSavedFoods();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SAVED_FOODS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads current food list from file
    private void loadCurrentFoods() {
        try {
            Gui.currentDayFood = jsonReaderCurrentFood.read();
            System.out.println("Loaded current day foods" + " from " + JSON_STORE_SAVED_FOODS);
            ConsumedFoodsPanel.setCurrentFoods();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SAVED_FOODS);
        }
    }


    public static void main(String[] args) {
        try {
            HomePanel app = new HomePanel();
        } catch (IOException e) {
            System.out.println("program failed");
        }

    }
}
