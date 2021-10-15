package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizManagerTest {

    private QuizManager quizManager;
    private Quiz quiz;
    private AttemptedQuiz attemptedQuiz;
    private Question question1;
    private Question question2;

    @BeforeEach
    void runBefore() {
        quizManager = new QuizManager();
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");

        quiz = new Quiz("My Quiz");

        question1 = new Question("Where is UBC?", "Vancouver", answers);
        question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        quizManager.addToAllQuizzesMade(quiz);
    }

    void initializeAttemptedQuiz() {
        List<String> answers = new ArrayList<>();
        answers.add("Ottawa");
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Victoria");

        Quiz quiz = new Quiz("Capital City Quiz");
        Question question = new Question("What is the Capital City of Canada?", "Ottawa", answers);
        quiz.addQuestion(question);

        attemptedQuiz = new AttemptedQuiz(quiz);
    }

    @Test
    void testGetAllQuizzesMade() {
        assertEquals(1, quizManager.getAllQuizzesMade().size());
        assertEquals(quiz, quizManager.getAllQuizzesMade().get(0));
    }

    @Test
    void testAddToAllQuizzesMade() {
        initializeAttemptedQuiz();

        assertEquals(1, quizManager.getAllQuizzesMade().size());
        quizManager.addToAllQuizzesMade(quiz);
        assertEquals(2, quizManager.getAllQuizzesMade().size());
        assertEquals(quiz, quizManager.getAllQuizzesMade().get(1));
    }

    @Test
    void testGetAttemptedQuizzes() {
        assertEquals(0, quizManager.getAttemptedQuizzes().size());
        AttemptedQuiz attempted = new AttemptedQuiz(quiz);
        quizManager.addToAttemptedQuizzes(attempted);
        assertEquals(1, quizManager.getAttemptedQuizzes().size());
        assertTrue(quizManager.getAttemptedQuizzes().contains(attempted));
    }

    @Test
    void testAddToAttemptedQuizzes() {
        initializeAttemptedQuiz();

        assertEquals(0, quizManager.getAttemptedQuizzes().size());
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        assertEquals(1, quizManager.getAttemptedQuizzes().size());
        assertEquals(attemptedQuiz, quizManager.getAttemptedQuizzes().get(0));
    }

    @Test
    void testGetAttemptedQuizFromName() {
        initializeAttemptedQuiz();
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        assertEquals(attemptedQuiz, quizManager.getAttemptedQuizFromName("Capital City Quiz"));
    }

    @Test
    void testGetQuizFromName() {
        assertEquals(quiz, quizManager.getQuizFromName("My Quiz"));
    }

    @Test
    void testGetQuizFromNameNameNotFound() {
        assertEquals(null, quizManager.getQuizFromName("Not My Quiz"));
    }

    @Test
    void testGetAttemptedQuizFromNameNotFound() {
        assertEquals(null, quizManager.getAttemptedQuizFromName("Not My Quiz"));
    }

    @Test
    void testOverallAttemptedQuestionsCorrect() {
        initializeAttemptedQuiz();
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        attemptedQuiz.checkAnswer(1, "Ottawa");
        assertEquals(1, quizManager.overallAttemptedQuestionsCorrect());
    }

    @Test
    void testOverallPotentialQuestionsCorrect() {
        initializeAttemptedQuiz();
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        attemptedQuiz.checkAnswer(1, "Vancouver");
        assertEquals(1, quizManager.overallPotentialQuestionsCorrect());
    }

    @Test
    void testOverallAttemptedPercentCorrectZeroPercent() {
        initializeAttemptedQuiz();
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        attemptedQuiz.checkAnswer(1, "Vancouver");
        assertEquals(0.0, quizManager.overallAttemptedQuestionsCorrect());
    }

    @Test
    void testOverallAttemptedPercentCorrectHundredPercent() {
        initializeAttemptedQuiz();
        quizManager.addToAttemptedQuizzes(attemptedQuiz);
        attemptedQuiz.checkAnswer(1, "Ottawa");
        assertEquals(100.0, quizManager.overallAttemptedPercentCorrect());
    }

}
