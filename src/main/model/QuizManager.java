package model;

import java.util.ArrayList;
import java.util.List;

// representation of a manager for the quizzes that are attempted and made.
public class QuizManager {

    private List<AttemptedQuiz> attemptedQuizzes;       // all attempted quizzes
    private List<Quiz> allQuizzesMade;                  // all quizzes created

    public QuizManager() {
        attemptedQuizzes = new ArrayList<>();
        allQuizzesMade = new ArrayList<>();
    }

    // EFFECTS: returns allQuizzesMade
    public List<Quiz> getAllQuizzesMade() {
        return allQuizzesMade;
    }

    // EFFECTS: returns attemptedQuizzes
    public List<AttemptedQuiz> getAttemptedQuizzes() {
        return attemptedQuizzes;
    }

    // MODIFIES: this
    // EFFECTS: adds quiz to allQuizzesMade
    public void addToAllQuizzesMade(Quiz quiz) {
        allQuizzesMade.add(quiz);
    }

    // MODIFIES: this
    // EFFECTS: adds attemptedQuiz to attemptedQuizzes
    public void addToAttemptedQuizzes(AttemptedQuiz attemptedQuiz) {
        attemptedQuizzes.add(attemptedQuiz);
    }

    // REQUIRES: There is a quiz in allQuizzesMade with name
    // EFFECTS: returns quiz with name identical to name parameter
    public Quiz getQuizFromName(String name) {
        for (Quiz next : allQuizzesMade) {
            if (next.getName().equalsIgnoreCase(name)) {
                return next;
            }
        }

        return new Quiz("Name not Found");
    }

    // REQUIRES: There is a quiz in attemptedQuizzes with name
    // EFFECTS: returns quiz with name identical to name parameter
    public AttemptedQuiz getAttemptedQuizFromName(String name) {
        for (AttemptedQuiz next : attemptedQuizzes) {
            String quizName = next.getQuiz().getName();
            if (quizName.equalsIgnoreCase(name)) {
                return next;
            }
        }

        return new AttemptedQuiz(new Quiz("Name not Found"));
    }


    // EFFECTS: returns total number of correct attempted questions
    public int overallAttemptedQuestionsCorrect() {
        int score = 0;
        for (AttemptedQuiz next : attemptedQuizzes) {
            score = score + next.getGrade();
        }

        return score;
    }

    // EFFECTS: returns total number of questions from all attempted quizzes
    public int overallPotentialQuestionsCorrect() {
        int questions = 0;
        for (AttemptedQuiz next : attemptedQuizzes) {
            Quiz quiz = next.getQuiz();
            questions = questions + quiz.numberOfQuestions();
        }

        return questions;
    }

    // EFFECTS: returns overall percentage of attempted questions gotten correct
    public double overallAttemptedPercentCorrect() {
        return ((1.0 * overallAttemptedQuestionsCorrect()) / overallPotentialQuestionsCorrect()) * 100;
    }


}
