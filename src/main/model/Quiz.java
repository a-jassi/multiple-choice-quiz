package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a quiz with a name and its questions
public class Quiz implements Writable {

    private String name;                    // name of the quiz
    private List<Question> questions;       // list of questions in the quiz

    // REQUIRES: name is not an empty String
    // EFFECTS: creates a new quiz with 0 questions in it
    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Quiz \"" + this.name + "\" was created"));
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
        EventLog.getInstance().logEvent(new Event("Question: \"" + question.getQuestion()
                + "\" added to the quiz: " + name));
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

    // The code for toJson and questionsListToJson references code from the WorkRoom class in
    // JsonSerializationDemo project
    // specifically the toJson() method for toJson() and thingiesToJson for questionsListToJson()
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", name);
        jsonObject.put("questions", questionsListToJson());

        return jsonObject;
    }

    // EFFECTS: returns questions in the form of a JSONArray
    private JSONArray questionsListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Question question : questions) {
            jsonArray.put(question.toJson());
        }

        return jsonArray;
    }

}
