package model;

import java.util.ArrayList;
import java.util.List;

// represents a quiz with a name and its questions
public class Quiz {

    private String name;                    // name of the quiz
    private List<Question> questions;       // list of questions in the quiz

    // REQUIRES: name is not an empty String
    // EFFECTS: creates a new quiz with 0 questions in it
    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    // EFFECTS: returns name of quiz
    public String getName() {
        return name;
    }

    // EFFECTS: returns questions
    public List<Question> getQuestions() {
        return questions;
    }

    // MODIFIES: this
    // EFFECTS: adds a question to list of questions
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // EFFECTS: returns String representation of question i in quiz
    public String stringOfQuestion(int i) {
        Question fetchedQuestion = questions.get(i - 1);
        return fetchedQuestion.toString();
    }

    // EFFECTS: returns the number of questions in a quiz
    public int numberOfQuestions() {
        return questions.size();
    }

    // REQUIRES: i <= quiz.numberOfQuestions() && i > 0
    // EFFECTS: returns answer to question i
    public String getAnswerToQuestion(int i) {
        return questions.get(i - 1).getCorrectAnswer();
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
