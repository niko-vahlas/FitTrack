package ui;

import model.Food;
import model.Foods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import static java.lang.Float.parseFloat;

// FoodDatabase Panel, the third panel
public class FoodDatabasePanel extends JPanel implements ListSelectionListener {
    private JList list;
    private static DefaultListModel listModel;

    private static final String addFoodString = "Add Food";
    private static final String deleteFoodString = "Delete Food";
    private JButton deleteButton;

    //EFFECTS: Creates food database panel
    public FoodDatabasePanel() {
        super(new BorderLayout());
        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton(addFoodString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addFoodString);
        addButton.addActionListener(addListener);

        deleteButton = new JButton(deleteFoodString);
        deleteButton.setActionCommand(deleteFoodString);
        deleteButton.addActionListener(new DeleteListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BorderLayout());
        buttonPane.add(deleteButton, BorderLayout.WEST);

        buttonPane.add(addButton, BorderLayout.EAST);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: Adds the previously saved foods to the list
    public static void setSavedFoods() {
        for (Food f : Gui.savedFoods.getFoods()) {
            float totalKcal = f.getFat() * 9 + 4 * f.getCarb() + 4 * f.getProtein();
            String newFood = f.getName() + " " + totalKcal + " kcal " + f.getProtein() + " protein " + "per "
                    +
                    f.getServing() + " " + f.getUnit();
            listModel.addElement(newFood);
        }
    }


    // Listener for delete food button
    class DeleteListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: performs deletion of food
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();

            String foodName = (String) listModel.getElementAt(index);
            String[] splitString = foodName.split(" ");
            Gui.savedFoods.removeFood(splitString[0]);
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable deleting.
                deleteButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the add button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = true;
        private JButton button;

        // EFFECTS: creates new listener for the add button
        public AddListener(JButton button) {
            this.button = button;
        }

        //MODIFIES: this
        // EFFECTS: adds food to the list and to saved foods
        public void actionPerformed(ActionEvent e) {
            String nameFood = JOptionPane.showInputDialog("Please enter the name of the food");
            float fatGrams = Float.parseFloat(JOptionPane
                    .showInputDialog("Please enter the grams of fat per serving size"));
            float proteinGrams = Float.parseFloat(JOptionPane
                    .showInputDialog("Please enter the grams of protein per serving size"));
            float carbGrams = Float.parseFloat(JOptionPane
                    .showInputDialog("Please enter the grams of carbohydrates per serving size"));
            String unit = JOptionPane.showInputDialog("Please enter the unit");
            float servingSize = Float.parseFloat(JOptionPane.showInputDialog("Please enter the serving size"));
            if (Gui.savedFoods.alreadyInDatabase(nameFood)) {
                System.out.println("The food is already present in the database");
                return;
            }
            Gui.savedFoods.addFood(new Food(fatGrams, carbGrams, proteinGrams, nameFood, servingSize, 0, unit));
            float totalKcal = fatGrams * 9 + 4 * carbGrams + 4 * proteinGrams;
            String insert = nameFood + " " + totalKcal + " kcal " + proteinGrams + " protein " + "per "
                    +
                    servingSize + " " + unit;
            listModel.addElement(insert);

        }

        //EFFECTS: Gives notification that something was inserted into document
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //EFFECTS: notifies that portion of the document has been removed
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //EFFECTS: notifies that attribute(s) were changed
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: enables button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: checks if the text field is empty and changes status of delete method
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // MODIFIES: this
    //EFFECTS: Enables and disables delete button
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable delete button.
                deleteButton.setEnabled(false);

            } else {
                //Selection, enable the delete button.
                deleteButton.setEnabled(true);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates gui
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Test()");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new FoodDatabasePanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: calls the create and show gui
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });


    }
}

