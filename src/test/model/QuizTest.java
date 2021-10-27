package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    private Quiz quiz;
    private Question question1;
    private Question question2;

    @BeforeEach
    void runBefore() {
        quiz = new Quiz("My Quiz");
    }

    void setUpTwoQuestionsInQuiz() {
        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        question1 = new Question("Where is UBC?", "Vancouver", answers);
        question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
    }

    @Test
    void testGetName() {
        assertEquals("My Quiz", quiz.getName());
    }

    @Test
    void testGetQuestions() {
        setUpTwoQuestionsInQuiz();

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
        setUpTwoQuestionsInQuiz();

        assertEquals(2, quiz.numberOfQuestions());
        assertEquals(question1, quiz.getQuestions().get(0));
        assertEquals(question2, quiz.getQuestions().get(1));
    }

    @Test
    void testStringOfQuestionQuestionOne() {
        setUpTwoQuestionsInQuiz();
        assertEquals("Where is UBC?" + "\n\n"
                + "a. Vancouver" + "\n"
                + "b. Toronto" + "\n"
                + "c. Seattle" + "\n"
                + "d. New York", quiz.stringOfQuestion(1));
    }

    @Test
    void testStringOfQuestionQuestionTwo() {
        setUpTwoQuestionsInQuiz();

        Question testQuestion = quiz.getQuestions().get(1);
        String returnedString = testQuestion.toString();
        assertEquals("Where is Times Square?" + "\n\n"
                + "a. Vancouver" + "\n"
                + "b. Toronto" + "\n"
                + "c. Seattle" + "\n"
                + "d. New York", quiz.stringOfQuestion(2));
    }


    @Test
    void testNumberOfQuestionsWhenZero() {
        assertEquals(0, quiz.numberOfQuestions());
    }

    @Test
    void testNumberOfQuestionsWhenMultiple() {
        setUpTwoQuestionsInQuiz();

        assertEquals(2, quiz.numberOfQuestions());
    }

    @Test
    void testGetAnswerToQuestion() {
        setUpTwoQuestionsInQuiz();

        assertEquals("Vancouver", quiz.getAnswerToQuestion(1));
        assertEquals("New York", quiz.getAnswerToQuestion(2));
    }

    @Test
    void testToString() {
        setUpTwoQuestionsInQuiz();

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

//    @Test
//    void testToJson() {
//        setUpTwoQuestionsInQuiz();
//
//        JSONObject quizAsJson = quiz.toJson();
//
//        assertEquals("My Quiz", quizAsJson.get("name"));
//
//        JSONArray questionsAsJson = quizAsJson.getJSONArray("questions");
//        List<Question> questionsForQuiz = quiz.getQuestions();
//
//        for (int i = 0; i < questionsForQuiz.size(); i++) {
//            assertEquals(questionsAsJson.get(i), questionsForQuiz.get(i));
//        }
//    }
//
//    @Test
//    void testQuestionsListToJson() {
//        setUpTwoQuestionsInQuiz();
//
//        JSONObject quizAsJson = quiz.toJson();
//        JSONArray questionsAsJson = quizAsJson.getJSONArray("questions");
//        List<Question> questionsForQuiz = quiz.getQuestions();
//
//        for (int i = 0; i < questionsForQuiz.size(); i++) {
//            assertEquals(, questionsForQuiz.get(i));
//        }
//
//
//    }



}
