package ui;

import model.Question;
import model.Quiz;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            new MultipleChoiceQuizApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the MultipleChoiceQuizApp: The file was not found");
        }
    }


}
