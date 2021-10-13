package model;

// represents an attempted quiz with the quiz itself, and the grade on it
public class AttemptedQuiz {

    public static final int POINTS_PER_CORRECT_ANSWER = 1;

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
    // EFFECTS: adds one to grade if answer is correct, nothing otherwise
    public void checkAnswer(int questionNum, String answer) {
        if (quiz.getAnswerToQuestion(questionNum).equals(answer)) {
            grade = grade + POINTS_PER_CORRECT_ANSWER;
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
