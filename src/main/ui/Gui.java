package ui;

import model.Food;
import model.Foods;


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

// Creates a Gui
public class Gui extends JPanel {

    static Foods savedFoods;
    static Foods currentDayFood;

    // Constructor for Gui, makes a 3 tabbedPane
    public Gui() throws IOException {
        super(new GridLayout(1, 1));
        currentDayFood = new Foods();
        savedFoods = new Foods();
        JTabbedPane tabbedPane = new JTabbedPane();

        HomePanel panel1 = new HomePanel();
        tabbedPane.addTab("Home", null, panel1,
                "Displays nutrition information");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ConsumedFoodsPanel panel2 = new ConsumedFoodsPanel();
        tabbedPane.addTab("Foods Consumed", null, panel2,
                "Shows foods that have been consumed");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        FoodDatabasePanel panel3 = new FoodDatabasePanel();
        tabbedPane.addTab("Food Database", null, panel3,
                "Shows foods that are present in database");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    // MODIFIES: this
    // EFFECTS: Creates a gui
    private static void createAndShowGUI() throws IOException {
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
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}