package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents a question with the question itself, the correct answer, and a list of 4 possible answers
public class Question implements Writable {

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

    // EFFECTS: returns string with html tags
    public String toStringAsHtml() {
        return "<html>" + this.question + "<br><br>" + "a. " + getPossibleAnswers().get(0)
                + "<br>" + "b. " + getPossibleAnswers().get(1)
                + "<br>" + "c. " + getPossibleAnswers().get(2)
                + "<br>" + "d. " + getPossibleAnswers().get(3)
                + "<br><br></html>";
    }

    // The code for toJson and possibleAnswersListToJson references code from the WorkRoom class in the
    // JsonSerializationDemo project
    // specifically the toJson() method for toJson() and thingiesToJson for possibleAnswersListToJson() below
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("question", question);
        jsonObject.put("correctAnswer", correctAnswer);
        jsonObject.put("possibleAnswers", possibleAnswersListToJson());

        return jsonObject;
    }

    // EFFECTS: returns possibleAnswers in the form of a JSON array
    public JSONArray possibleAnswersListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String answer : possibleAnswers) {
            jsonArray.put(answer);
        }

        return jsonArray;
    }

}
