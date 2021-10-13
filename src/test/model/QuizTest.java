package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    private Quiz quiz;

    @BeforeEach
    void runBefore() {
        quiz = new Quiz("My Quiz");
    }

    @Test
    void testGetName() {
        assertEquals("My Quiz", quiz.getName());
    }

    @Test
    void testGetQuestions() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        assertEquals(2, quiz.numberOfQuestions());
        assertEquals(question1, quiz.getQuestions().get(0));
        assertEquals(question2, quiz.getQuestions().get(1));
    }

    @Test
    void testAddQuestionAddOne() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);

        quiz.addQuestion(question1);

        assertEquals(1, quiz.numberOfQuestions());
        assertEquals(question1, quiz.getQuestions().get(0));
    }

    @Test
    void testAddQuestionAddMultiple() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        assertEquals(2, quiz.numberOfQuestions());
        assertEquals(question1, quiz.getQuestions().get(0));
        assertEquals(question2, quiz.getQuestions().get(1));
    }

    @Test
    void testRemoveQuestion() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        quiz.removeQuestion(question1);

        assertEquals(1, quiz.numberOfQuestions());
        assertEquals(question2, quiz.getQuestions().get(0));
    }

    @Test
    void testNumberOfQuestionsWhenZero() {
        assertEquals(0, quiz.numberOfQuestions());
    }

    @Test
    void testNumberOfQuestionsWhenMultiple() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        assertEquals(2, quiz.numberOfQuestions());
    }

    @Test
    void testGetAnswerToQuestion() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        assertEquals("Vancouver", quiz.getAnswerToQuestion(1));
        assertEquals("New York", quiz.getAnswerToQuestion(2));
    }

    @Test
    void testToString() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        String expectedOutputString = "Welcome to My Quiz!\n" +
                "\n" +
                "1. Where is UBC?\n\n" +
                "a. Vancouver\n" +
                "b. Toronto\n" +
                "c. Seattle\n" +
                "d. New York\n\n" +
                "2. Where is Times Square?\n\n" +
                "a. Vancouver\n" +
                "b. Toronto\n" +
                "c. Seattle\n" +
                "d. New York\n\n";

        assertEquals(expectedOutputString, quiz.toString());
    }



}
