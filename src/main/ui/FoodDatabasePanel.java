package ui;

import model.Food;
import model.Foods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import static java.lang.Float.parseFloat;

/* Test().java requires no other files. */
public class FoodDatabasePanel extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addFoodString = "Add Food";
    private static final String deleteFoodString = "Delete Food";
    private JButton deleteButton;
    private JTextField foodName;
    private Foods currentFoods;
    private Foods savedFoods;


    public FoodDatabasePanel() {
        super(new BorderLayout());
        //this.currentFoods = currentFoods;
        //this.savedFoods = savedFoods;
        listModel = new DefaultListModel();
        /*for (Food f : currentFoods.getFoods()) {
            String name = f.getName();
            String unit = f.getUnit();
            String consumed = String.valueOf(f.getConsumed());
            int nameLength = name.length();
            int unitLength = unit.length();
            int consumedLength = consumed.length();

         */
        listModel.addElement("Example food 0 0");


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
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
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

    /*public void setSavedFoods(Foods savedFoods) {
        this.savedFoods = savedFoods;
        for (Food f : this.savedFoods.getFoods()) {
            String newFood = f.getName() + " " + f.getProtein();

            listModel.addElement(newFood);
        }
    }*/

    public void setSavedFoods() {
        savedFoods = Gui.savedFoods;
    }



    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();

            String foodName = (String) listModel.getElementAt(index);
            Gui.savedFoods.removeFood(foodName);
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
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

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = foodName.getText();
            String[] splitString = name.split(" ");

            //User didn't type in a unique name...
            if ((splitString[0].equals("")) || alreadyInDatabase(splitString[3]))  {
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


            //If we just wanted to add to the end, we'd do this:
            listModel.addElement(foodName.getText());
            Gui.savedFoods.addFood(new Food(parseFloat(splitString[0]), parseFloat(splitString[1]), parseFloat(splitString[2]), splitString[3], parseFloat(splitString[4]), parseFloat(splitString[5]),splitString[6]));


            //Reset the text field.
            foodName.requestFocusInWindow();
            foodName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInDatabase(String name) {
            for (Food f : Gui.savedFoods.getFoods()) {
                if (f.getName().equals(name)) {
                    return true;
                }

            }
            return false;
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                deleteButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                deleteButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
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

