package model;

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
}