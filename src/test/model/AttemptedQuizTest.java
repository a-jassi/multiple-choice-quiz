package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.AttemptedQuiz.POINTS_PER_CORRECT_ANSWER;
import static org.junit.jupiter.api.Assertions.*;
public class AttemptedQuizTest {

    private AttemptedQuiz attemptedQuiz;
    private Quiz quiz;

    @BeforeEach
    void runBefore() {
        List<String> answers = new ArrayList<>();
        answers.add("Ottawa");
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Victoria");

        Quiz quiz = new Quiz("Capital City Quiz");
        Question question = new Question("What is the Capital City of Canada?", "Ottawa", answers);
        quiz.addQuestion(question);

        this.quiz = quiz;
        this.attemptedQuiz = new AttemptedQuiz(quiz);
    }

    @Test
    void testGetQuiz() {
        assertEquals(quiz, attemptedQuiz.getQuiz());
    }

    @Test
    void testGetGrade() {
        assertEquals(0, attemptedQuiz.getGrade());
    }

    @Test
    void testGetGradeHigherThanZero() {
        attemptedQuiz.checkAnswer(1,"Ottawa");
        assertEquals(POINTS_PER_CORRECT_ANSWER, attemptedQuiz.getGrade());
    }

    @Test
    void testCheckAnswerRightAnswer() {
        assertEquals(0, attemptedQuiz.getGrade());
        attemptedQuiz.checkAnswer(1,"Ottawa");
        assertEquals(POINTS_PER_CORRECT_ANSWER, attemptedQuiz.getGrade());
    }

    @Test
    void testCheckAnswerWrongAnswer() {
        assertEquals(0, attemptedQuiz.getGrade());
        attemptedQuiz.checkAnswer(1,"Vancouver");
        assertEquals(0, attemptedQuiz.getGrade());
    }

    @Test
    void testCheckIfPassedNotAttempted() {
        assertFalse(attemptedQuiz.checkIfPassed());
    }

    @Test
    void testCheckIfPassedWhenPassed() {
        attemptedQuiz.checkAnswer(1,"Ottawa");
        assertTrue(attemptedQuiz.checkIfPassed());
    }

    @Test
    void testCheckIfPassedWhenAttemptedButFailed() {
        attemptedQuiz.checkAnswer(1,"Vancouver");
        assertFalse(attemptedQuiz.checkIfPassed());
    }

    @Test
    void testGradeAsPercentZeroPercent() {
        attemptedQuiz.checkAnswer(1,"Toronto");
        assertEquals(0, attemptedQuiz.getGradeAsPercent());
    }

    @Test
    void testGradeAsPercentHundredPercent() {
        attemptedQuiz.checkAnswer(1,"Ottawa");
        assertEquals(100.0, attemptedQuiz.getGradeAsPercent());
    }

    @Test
    void testToString() {
        String expectedOutputString = "Welcome to Capital City Quiz!\n" +
                "\n" +
                "1. What is the Capital City of Canada?\n\n" +
                "a. Ottawa\n" +
                "b. Vancouver\n" +
                "c. Toronto\n" +
                "d. Victoria\n\n" +
                "You got 0.0% on this attempt!";


        assertEquals(expectedOutputString, attemptedQuiz.toString());
    }

}
