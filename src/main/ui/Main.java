package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MultipleChoiceQuizApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the MultipleChoiceQuizApp: The file was not found");
        }
    }


}
