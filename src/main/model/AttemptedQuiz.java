package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;

// represents an attempted quiz with the quiz itself, and the grade on it
public class AttemptedQuiz implements Writable {

    private final Quiz quiz;                // quiz that was attempted
    private int grade;                      // grade on quiz

    // EFFECTS: constructs new attempted quiz
    public AttemptedQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.grade = 0;
    }

    // EFFECTS: returns quiz
    public Quiz getQuiz() {
        return quiz;
    }

    // EFFECTS: returns grade
    public int getGrade() {
        return grade;
    }

    // MODIFIES: this
    // EFFECTS: adds one to grade if answer is correct and returns true, nothing otherwise happens and return false
    public boolean checkAnswer(int questionNum, String answer) {
        if (questionNum <= quiz.numberOfQuestions() && questionNum > 0) {
            String answerToQuestion = quiz.getAnswerToQuestion(questionNum);
            if (answerToQuestion.equals(answer)) {
                grade++;
                return true;
            }
            return false;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: sets grade to newGrade
    public void setGrade(int newGrade) {
        this.grade = newGrade;
    }

    // The idea for how to implement shuffleAnswers() comes from the stackoverflow link below:
    // https://stackoverflow.com/questions/16112515/how-to-shuffle-an-arraylist

    // MODIFIES: this
    // EFFECTS: shuffles orders of answers in questions
    public void shuffleAnswers() {
        for (Question next : quiz.getQuestions()) {
            Collections.shuffle(next.getPossibleAnswers());
        }
    }

    // EFFECTS: returns grade as a percent of total questions
    public double getGradeAsPercent() {
        return ((1.0 * grade) / quiz.numberOfQuestions()) * 100;
    }

    // EFFECTS: returns true if quiz scored >= 50 percent , false if not higher than 50
    public boolean checkIfPassed() {
        return (getGradeAsPercent() >= 50);
    }

    // EFFECTS: return string representation of attempted quiz
    @Override
    public String toString() {
        return quiz + "You got " + getGradeAsPercent() + "% on this attempt!";
    }

    // The code for toJson references code from the WorkRoom class in
    // JsonSerializationDemo project
    // specifically the toJson() method for toJson()
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("quiz", quiz.toJson());
        jsonObject.put("grade", grade);

        return jsonObject;
    }
}
