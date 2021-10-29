package persistence;



// Most of this JsonReader class is loosely templated off the JsonReader class in the JsonSerializationDemo
// link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java

import model.AttemptedQuiz;
import model.Question;
import model.Quiz;
import model.QuizManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// represents a JsonReader object with the source file name
// it reads the saved quizManager state from JSON data stored in file
public class JsonReader {

    private String fileToRead;      // name to the file to read (local path only)

    // EFFECTS: constructs a new JsonReader object that reads from Json source file
    public JsonReader(String fileToRead) {
        this.fileToRead = fileToRead;
    }

    //EFFECTS: reads QuizManager from file and returns it
    //         throws IOException if there is an error trying to read jsonData from the file
    public QuizManager read() throws IOException {
        String jsonData = readContentsFromFile(fileToRead);
        JSONObject jsonObject = new JSONObject(jsonData);
        return getQuizManagerFromJson(jsonObject);
    }

    // readContentsFromFile is directly taken from JsonReader class in JsonSerializationDemo
    // link is at top of this class

    // EFFECTS: reads contents of fileToRead and returns it as a string
    private String readContentsFromFile(String fileToRead) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileToRead), StandardCharsets.UTF_8)) {
            stream.forEach(s -> fileContent.append(s));
        }

        return fileContent.toString();
    }

    // EFFECTS: gets QuizManager from jsonObject and returns it
    private QuizManager getQuizManagerFromJson(JSONObject jsonObject) {
        QuizManager quizManager = new QuizManager();
        addAttemptedQuizzes(quizManager, jsonObject);
        addAllQuizzesMade(quizManager, jsonObject);
        return quizManager;
    }

    // MODIFIES: quizManager
    // EFFECTS: gets attemptedQuizzes from jsonObject and adds them to quizManager
    private void addAttemptedQuizzes(QuizManager quizManager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("attemptedQuizzes");
        for (Object next : jsonArray) {
            JSONObject nextAttemptedQuiz = (JSONObject) next;
            addAttemptedQuiz(quizManager, nextAttemptedQuiz);
        }
    }

    // MODIFIES: quizManager
    // EFFECTS: get allMadeQuizzes from jsonObject and adds them to quizManager
    private void addAllQuizzesMade(QuizManager quizManager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allQuizzesMade");
        for (Object next : jsonArray) {
            JSONObject nextQuiz = (JSONObject) next;
            addQuiz(quizManager, nextQuiz);
        }
    }

    // MODIFIES: quizManager
    // EFFECTS: adds quiz to quizManager
    private void addQuiz(QuizManager quizManager, JSONObject nextQuiz) {
        quizManager.addToAllQuizzesMade(getQuizFromJsonAllQuizzesMade(nextQuiz));
    }

    // MODIFIES: quizManager
    // EFFECTS: gets attemptedQuiz from JSONObject and adds it to QuizManager
    private void addAttemptedQuiz(QuizManager quizManager, JSONObject jsonObject) {
        int grade = jsonObject.getInt("grade");
        Quiz quiz = getQuizFromJson(jsonObject);
        AttemptedQuiz attempted = new AttemptedQuiz(quiz);
        attempted.setGrade(grade);
        quizManager.addToAttemptedQuizzes(attempted);
    }

    // EFFECTS: gets Quiz from jsonObject and returns it
    private Quiz getQuizFromJson(JSONObject jsonObject) {
        JSONObject quizAsJson = jsonObject.getJSONObject("quiz");
        String name = quizAsJson.getString("name");
        Quiz newQuiz = new Quiz(name);
        addQuestions(newQuiz, quizAsJson);
        return newQuiz;
    }

    // EFFECTS: constructs the written quiz stored in jsonObject and returns it
    private Quiz getQuizFromJsonAllQuizzesMade(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Quiz newQuiz = new Quiz(name);
        addQuestions(newQuiz, jsonObject);
        return newQuiz;
    }

    // MODIFIES: quiz
    // EFFECTS: adds all questions to quiz
    private void addQuestions(Quiz quiz, JSONObject jsonObject) {
        // JSONObject quizAsJson = jsonObject.getJSONObject("quiz");
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object next : jsonArray) {
            JSONObject nextAsJson = (JSONObject) next;
            addQuestion(quiz, nextAsJson);
        }
    }

    // MODIFIES: quiz
    // EFFECTS: creates saved question and adds it to quiz
    private void addQuestion(Quiz quiz, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String correctAnswer = jsonObject.getString("correctAnswer");
        List<String> possibleAnswers = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("possibleAnswers");

        for (Object next : jsonArray) {
            String nextPossibleAnswerAsString = next.toString();
            possibleAnswers.add(nextPossibleAnswerAsString);
        }

        Question returnedQuestion = new Question(question, correctAnswer, possibleAnswers);
        quiz.addQuestion(returnedQuestion);
    }

}
