package persistence;

import model.AttemptedQuiz;
import model.Question;
import model.Quiz;
import model.QuizManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    // the idea for these 3 specific test cases was taken from JsonWriterDemo in JsonSerializationDemo
    // link below:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java

    @Test
    void testJsonWriterNotValidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/quiz\0invalid:filename.json");
            jsonWriter.open();
            fail("Test failed: An IOException was supposed to be thrown");
        } catch (IOException e) {
            // expected behaviour
        }
    }

    @Test
    void testJsonWriterSaveEmptyQuizManager() {

        try {
            QuizManager quizManager = new QuizManager();
            JsonWriter jsonWriter = new JsonWriter("./data/testJsonWriterSaveEmptyManager.json");
            jsonWriter.open();
            jsonWriter.write(quizManager);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testJsonWriterSaveEmptyManager.json");
            quizManager = jsonReader.read();
            assertEquals(0, quizManager.getAllQuizzesMade().size());
            assertEquals(0, quizManager.getAttemptedQuizzes().size());
        } catch (IOException e) {
            fail("Test Failed: No exception should have been thrown in this test");
        }

    }

    @Test
    void testJsonWriterSaveManagerWithData() {
        try {
            QuizManager quizManager = init();
            JsonWriter jsonWriter = new JsonWriter("./data/testJsonWriterSaveEmptyManager.json");
            jsonWriter.open();
            jsonWriter.write(quizManager);
            jsonWriter.close();

            JsonReader reader = new JsonReader("./data/testJsonWriterSaveEmptyManager.json");
            quizManager = reader.read();
            assertEquals(1, quizManager.getAllQuizzesMade().size());
            assertEquals(1, quizManager.getAttemptedQuizzes().size());

        } catch (IOException e) {
            fail("Test Failed: No exception should have been thrown in this test");
        }
    }

    // EFFECTS: initializes a quizManager with some data (1 quiz, 1 attempted quiz)
    private QuizManager init() {
        QuizManager quizManager = new QuizManager();
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");

        Quiz quiz = new Quiz("My Quiz");

        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        quizManager.addToAllQuizzesMade(quiz);

        AttemptedQuiz attemptedQuiz = new AttemptedQuiz(quiz);
        attemptedQuiz.setGrade(1);
        quizManager.addToAttemptedQuizzes(attemptedQuiz);

        return quizManager;
    }


}
