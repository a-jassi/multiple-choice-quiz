package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(1, attemptedQuiz.getGrade());
    }

    @Test
    void testCheckAnswerRightAnswer() {
        assertEquals(0, attemptedQuiz.getGrade());
        attemptedQuiz.checkAnswer(1,"Ottawa");
        assertEquals(1, attemptedQuiz.getGrade());
    }

    @Test
    void testCheckAnswerWrongAnswer() {
        assertEquals(0, attemptedQuiz.getGrade());
        attemptedQuiz.checkAnswer(1,"Vancouver");
        assertEquals(0, attemptedQuiz.getGrade());
    }

    @Test
    void testShuffleAnswers() {
        attemptedQuiz.shuffleAnswers();
        Question questionForQuiz = quiz.getQuestions().get(0);
        List<String> possibleAnswers = questionForQuiz.getPossibleAnswers();
        assertTrue(possibleAnswers.contains("Ottawa"));
        assertTrue(possibleAnswers.contains("Vancouver"));
        assertTrue(possibleAnswers.contains("Toronto"));
        assertTrue(possibleAnswers.contains("Victoria"));
        assertEquals(4, possibleAnswers.size() );
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
    void testCheckIfPassedFiftyPercent() {
        List<String> answers = new ArrayList<>();
        answers.add("Ottawa");
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Victoria");

        Quiz quiz = new Quiz("Capital City Quiz");
        Question question = new Question("What is the Capital City of Canada?", "Ottawa", answers);
        quiz.addQuestion(question);
        Question question2 = new Question("Where is the CN Tower", "Toronto", answers);
        quiz.addQuestion(question2);
        attemptedQuiz = new AttemptedQuiz(quiz);

        attemptedQuiz.checkAnswer(1, "Ottawa");
        attemptedQuiz.checkAnswer(2, "Victoria");
        assertTrue(attemptedQuiz.checkIfPassed());
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
