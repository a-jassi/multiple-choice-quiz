package persistence;

// Every method of this JsonWriter class references the JsonWriter class in the JsonSerializationDemo
// The names are the same for every method, so open() references open() in JsonSerializationDemo, etc.
// link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonWriter.java

import model.QuizManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a JsonWriter object with a PrintWriter and destination.
// It writes the JSON representation of a QuizManager to file
public class JsonWriter {

    private static final int INDENT = 4;        // amount of spaces for an indent
    private PrintWriter printWriter;            // object that writes to file
    private String fileWrittenOn;               // path of file that is written on

    // EFFECTS: creates a JsonWriter object with a file's path that is written on, and the object that writes on it
    public JsonWriter(String fileWrittenOn) {
        this.fileWrittenOn = fileWrittenOn;
    }

    // MODIFIES: this
    // EFFECTS: opens the printWriter with fie that is going to be written on
    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(fileWrittenOn));
    }

    // MODIFIES: this
    // EFFECTS: writes Json representation of a QuizManager to file
    public void write(QuizManager quizManager) {
        JSONObject jsonObject = quizManager.toJson();
        saveToFile(jsonObject.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes the printWriter
    public void close() {
        printWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes jsonString to the fileWrittenOn
    private void saveToFile(String jsonString) {
        printWriter.print(jsonString);
    }
}
