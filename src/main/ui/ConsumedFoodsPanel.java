package ui;

import model.Food;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Represents the consumed foods panel, the second panel
public class ConsumedFoodsPanel extends JPanel implements ListSelectionListener {
    private JList list;
    private static DefaultListModel listModel;

    private static final String addFoodString = "Add Food";
    private static final String deleteFoodString = "Delete Food";
    private JButton deleteButton;
    private JTextField foodName;

    // Constructs the consumed foods panel
    public ConsumedFoodsPanel() {
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
        addButton.setEnabled(false);
        deleteButton = new JButton(deleteFoodString);
        deleteButton.setActionCommand(deleteFoodString);
        deleteButton.addActionListener(new DeleteListener());
        foodName = new JTextField(10);
        foodName.addActionListener(addListener);
        foodName.getDocument().addDocumentListener(addListener);

        extracted(listScrollPane, addButton);
    }

    // EFFECTS: Extracted method from above, adds new JPane and lays out buttons and list
    private void extracted(JScrollPane listScrollPane, JButton addButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(foodName);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    //EFFECTS: constructs delete button
    private void extracted() {

    }

    //MODIFIES: this
    //EFFECTS: sets the current food list
    public static void setCurrentFoods() {
        for (Food f : Gui.currentDayFood.getFoods()) {
            String newFood = f.getName() + " " + f.calculateCalories() + " kcal";

            listModel.addElement(newFood);

        }
    }


    /// Listener for delete food button
    class DeleteListener implements ActionListener {
        // MODIFIES: this and currentDayFood
        // EFFECTS: deletes selected food
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();

            String foodName = (String) listModel.getElementAt(index);
            String[] splitString = foodName.split(" ");
            Gui.currentDayFood.removeFoodConsumed(splitString[0]);
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //No foods are left, disable deleting.
                deleteButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed food in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // This listener is shared by the text field and the add button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: Makes new add listener button
        public AddListener(JButton button) {
            this.button = button;
        }

        //MODIFIES: this
        //EFFECTS: Performs the adding of a food
        public void actionPerformed(ActionEvent e) {
            String name = foodName.getText();

            //User didn't type in a unique food...
            if ((name.equals("")) || (!Gui.savedFoods.notInDatabase(name)))  {
                Toolkit.getDefaultToolkit().beep();
                foodName.requestFocusInWindow();
                foodName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            float consumed = Float.parseFloat(JOptionPane.showInputDialog("How much was consumed?"));
            Food addFood = Gui.savedFoods.eatFood(name, consumed);
            Gui.currentDayFood.consumeFood(addFood);
            listModel.addElement(name + " " + addFood.calculateCalories() + " kcal");


            //Reset the text field.
            foodName.requestFocusInWindow();
            foodName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }



        //EFFECTS: Creates a copy of a food and sets its consumed field


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
        JComponent newContentPane = new ConsumedFoodsPanel();
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

