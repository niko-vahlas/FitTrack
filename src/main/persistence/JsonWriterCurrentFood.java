package persistence;

import model.Food;
import model.Foods;
import ui.FitnessApp;
import org.json.JSONObject;


import java.io.*;
import java.util.List;

// Represents a writer that writes Foods representation of workroom to file
public class JsonWriterCurrentFood {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterCurrentFood(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of food list to file
    public void writeCurrentFoods(Foods sf) {
        JSONObject json = sf.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}