package model;

import java.util.Collections;

// represents an attempted quiz with the quiz itself, and the grade on it
public class AttemptedQuiz {

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

        } else {
            System.out.println("Invalid Question Number!");
        }

        return false;
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


}
