package persistence;

import model.AttemptedQuiz;
import model.Quiz;
import model.QuizManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    // The idea for these 3 specific test cases comes from JsonReaderTest in JsonSerializationDemo
    // link:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java

    @Test
    void testJsonReaderNoSuchFile() {
        JsonReader reader = new JsonReader("./data.madeUpFile.json");
        try {
            QuizManager quizManager = reader.read();
            fail("Test Failed: IO Exception was supposed to be thrown");
        } catch (IOException e) {
            // expected behaviour
        }
    }

    @Test
    void testJsonReaderNoDataInQuizManager() {
        JsonReader reader = new JsonReader("./data/testJsonReaderForNoDataInQuizManager.json");

        try {
            QuizManager quizManager = reader.read();
            List<Quiz> allQuizzes = quizManager.getAllQuizzesMade();
            assertEquals(0, allQuizzes.size());
            List<AttemptedQuiz> allAttempted = quizManager.getAttemptedQuizzes();
            assertEquals(0, allAttempted.size());
        } catch (IOException e) {
            fail("Failed Test: File could not be read enough though it should have been");
        }
    }

    @Test
    void testJsonReaderWithDataInQuizManager() {
        JsonReader reader = new JsonReader("./data/testJsonReaderForDataInQuizManager.json");

        try {
            QuizManager quizManager = reader.read();
            List<AttemptedQuiz> allAttempted = quizManager.getAttemptedQuizzes();
            List<Quiz> allQuizzes = quizManager.getAllQuizzesMade();
            assertEquals(2, allAttempted.size());
            assertEquals(2, allQuizzes.size());
            AttemptedQuiz attemptedTwo = allAttempted.get(1);
            AttemptedQuiz attemptedOne = allAttempted.get(0);
            assertEquals(1, attemptedTwo.getGrade());
            assertEquals(2.0, attemptedOne.getGrade());
        } catch (IOException e) {
            fail("Failed test: File could not be read even though it should have been able to");
        }
    }
}
