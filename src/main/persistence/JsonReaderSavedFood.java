package persistence;

import model.Foods;
import model.Food;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderSavedFood {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderSavedFood(String source) {
        this.source = source;
    }

    // EFFECTS: reads fitness from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Foods read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoods(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Foods parseFoods(JSONObject jsonObject) {
        Foods foods = new Foods();
        addFoods(foods, jsonObject);
        return foods;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addFoods(Foods foods, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Foods");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addFood(foods, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addFood(Foods foods, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        float fat = (jsonObject.getFloat("fat"));
        float carb = (jsonObject.getFloat("carb"));
        float protein = (jsonObject.getFloat("protein"));
        float serving = (jsonObject.getFloat("serving"));
        String unit = jsonObject.getString("unit");
        float consumed = (jsonObject.getFloat("consumed"));
        Food food = new Food(fat, carb, protein, name, serving, consumed, unit);
        foods.addFood(food);
    }

}

