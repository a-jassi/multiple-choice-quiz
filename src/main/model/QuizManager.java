package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// representation of a manager for the quizzes that are attempted and made.
public class QuizManager implements Writable {

    private List<AttemptedQuiz> attemptedQuizzes;       // all attempted quizzes
    private List<Quiz> allQuizzesMade;                  // all quizzes created

    // EFFECTS: creates a new QuizManager object with empty list of attempted quizzes and made quizzes
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

    // EFFECTS: returns quiz with name identical to name parameter
    public Quiz getQuizFromName(String name) {
        for (Quiz next : allQuizzesMade) {
            if (next.getName().equalsIgnoreCase(name)) {
                return next;
            }
        }

        return null;
    }

    // EFFECTS: returns quiz with name identical to name parameter
    public AttemptedQuiz getAttemptedQuizFromName(String name) {
        for (AttemptedQuiz next : attemptedQuizzes) {
            String quizName = next.getQuiz().getName();
            if (quizName.equalsIgnoreCase(name)) {
                return next;
            }
        }

        return null;
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

    // The code for toJson(), attemptedQuizzesListToJson() and allQuizzesMadeListToJson references code
    // from the WorkRoom class in the JsonSerializationDemo project
    // specifically the toJson() method for toJson()
    // and thingiesToJson for attemptedQuizzesListToJson() and allQuizzesMadeListToJson()
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/WorkRoom.java

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("attemptedQuizzes", attemptedQuizzesListToJson());
        jsonObject.put("allQuizzesMade", allQuizzesMadeListToJson());

        return jsonObject;
    }

    // EFFECTS: returns attemptedQuizzes in the form of a JSONArray
    private JSONArray attemptedQuizzesListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (AttemptedQuiz next : attemptedQuizzes) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns allQuizzesMade in the form of a JSONArray
    private JSONArray allQuizzesMadeListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Quiz next : allQuizzesMade) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}
