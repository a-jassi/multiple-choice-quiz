package model;

import java.util.List;

// Represents a question with the question itself, the correct answer, and a list of 4 possible answers
public class Question {

    private String question;                    // question itself
    private String correctAnswer;               // correct answer to question
    private List<String> possibleAnswers;       // 4 possible answers in a list

    // EFFECTS: creates a new question object
    public Question(String question, String correctAnswer, List<String> possibleAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.possibleAnswers = possibleAnswers;
    }

    // EFFECTS: returns question
    public String getQuestion() {
        return question;
    }

    // EFFECTS: returns correctAnswer
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // EFFECTS: returns possibleAnswers
    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    // EFFECTS: returns string representation of a Question
    @Override
    public String toString() {
        return this.question + "\n\n" + "a. " + getPossibleAnswers().get(0)
                + "\n" + "b. " + getPossibleAnswers().get(1)
                + "\n" + "c. " + getPossibleAnswers().get(2)
                + "\n" + "d. " + getPossibleAnswers().get(3);
    }

}