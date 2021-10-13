package model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private String name;
    private List<Question> questions;       // list of questions in the quiz
    private int grade;                      // grade on a quiz


    // EFFECTS: creates a new quiz with 0 questions in it
    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.grade = 0;
    }

    // EFFECTS: returns name of quiz
    public String getName() {
        return name;
    }

    // EFFECTS: returns questions
    public List<Question> getQuestions() {
        return questions;
    }

    // EFFECTS: returns grade on quiz
    public int getGrade() {
        return grade;
    }

    // MODIFIES: this
    // EFFECTS: sets new value for grade
    public void setGrade(int newGrade) {
        grade = newGrade;
    }

    // MODIFIES: this
    // EFFECTS: adds a question to list of questions
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // REQUIRES: question is in the list of questions
    // MODIFIES: this
    // EFFECTS: removes question from the list of questions
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    // EFFECTS: returns the number of questions in a quiz
    public int numberOfQuestions() {
        return questions.size();
    }

    // EFFECTS: returns string representation of a Quiz
    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append("Welcome to " + this.name + "!\n\n");
        for (int i = 0; i < questions.size(); i++) {
            returnedString.append((i + 1) + ". ");
            returnedString.append(questions.get(i));
            returnedString.append("\n\n");
        }

        return returnedString.toString();
    }






}
