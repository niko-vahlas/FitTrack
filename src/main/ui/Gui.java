package ui;

import model.Food;
import model.Foods;
import persistence.JsonReaderCurrentFood;
import persistence.JsonReaderSavedFood;
import persistence.JsonWriterCurrentFood;
import persistence.JsonWriterSavedFood;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class Gui extends JPanel {

    private static final String JSON_STORE_SAVED_FOODS = "./data/savedfoods.json";
    private static final String JSON_STORE_CURRENT_FOODS = "./data/currentfoods.json";
    private Scanner input;
    static Foods savedFoods;
    static Foods currentDayFood;

    private JsonWriterCurrentFood jsonWriterCurrentFood;
    private JsonWriterSavedFood jsonWriterSavedFood;
    private JsonReaderCurrentFood jsonReaderCurrentFood;
    private JsonReaderSavedFood jsonReaderSavedFood;

    public Gui() {
        super(new GridLayout(1, 1));
        currentDayFood = new Foods();
        savedFoods = new Foods();
        JTabbedPane tabbedPane = new JTabbedPane();

        //JComponent panel1 = makeTextPanel("Panel #1");
        JComponent panel1 = makeTextPanel("Oanel 2");
        tabbedPane.addTab("Home", null, panel1,
                "Displays nutrition information");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ConsumedFoodsPanel panel2 = new ConsumedFoodsPanel();
        tabbedPane.addTab("Foods Consumed", null, panel2,
                "Shows foods that have been consumed");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        panel2.setCurrentFoods();
        panel2.setSavedFoods();

        FoodDatabasePanel panel3 = new FoodDatabasePanel();
        tabbedPane.addTab("Food Database", null, panel3,
                "Shows foods that are present in database");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        panel3.setSavedFoods();

        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", null, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("NutritionTracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
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