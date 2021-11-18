package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question question;

    @BeforeEach
    void runBefore() {
        List<String> answers = new ArrayList<>();
        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("4");
        question = new Question("What is 1 + 1?", "2", answers);
    }

    @Test
    void testGetQuestion() {
        assertEquals("What is 1 + 1?", question.getQuestion());
    }

    @Test
    void testGetCorrectAnswer() {
        assertEquals("2", question.getCorrectAnswer());
    }

    @Test
    void testGetPossibleAnswers() {
        for (int i = 0; i < question.getPossibleAnswers().size(); i++) {
            List<String> answers = question.getPossibleAnswers();
            String answer = answers.get(i);
            String indexInString = (i + 1) + "";
            assertEquals(answer, indexInString);
        }
    }

    @Test
    void testToString() {
        String returnedString = question.toString();
        assertEquals("What is 1 + 1?" + "\n\n"
                + "a. 1" + "\n"
                + "b. 2" + "\n"
                + "c. 3" + "\n"
                + "d. 4", returnedString);
    }

    @Test
    void testToStringAsHtml() {
        String returnedString = question.toStringAsHtml();
        assertEquals("<html>What is 1 + 1?<br><br>"
                + "a. 1<br>"
                + "b. 2<br>"
                + "c. 3<br>"
                + "d. 4<br><br></html>", returnedString);
    }

    @Test
    void testToJson() {
        JSONObject questionAsJson = question.toJson();

        assertEquals("What is 1 + 1?", questionAsJson.get("question"));
        assertEquals("2", questionAsJson.get("correctAnswer"));

        JSONArray answersAsJson = (JSONArray) questionAsJson.get("possibleAnswers");
        List<String> possibleAnswersForQuestion = question.getPossibleAnswers();

        for (int i = 0; i < possibleAnswersForQuestion.size(); i++) {
            assertEquals(answersAsJson.get(i), possibleAnswersForQuestion.get(i));
        }
    }

    @Test
    void testPossibleAnswersListToJson() {
        JSONObject questionAsJson = question.toJson();
        JSONArray answersAsJson = (JSONArray) questionAsJson.get("possibleAnswers");
        List<String> possibleAnswersForQuestion = question.getPossibleAnswers();

        for (int i = 0; i < possibleAnswersForQuestion.size(); i++) {
            assertEquals(answersAsJson.get(i), possibleAnswersForQuestion.get(i));
        }
    }
}