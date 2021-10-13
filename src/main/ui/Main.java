package ui;

import model.Question;
import model.Quiz;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Quiz quiz = new Quiz("My Quiz");

        List<String> answers = new ArrayList<>();
        answers.add("Vancouver");
        answers.add("Toronto");
        answers.add("Seattle");
        answers.add("New York");
        Question question1 = new Question("Where is UBC?", "Vancouver", answers);
        Question question2 = new Question("Where is Times Square?", "New York", answers);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);

        System.out.println(quiz);
    }


}
