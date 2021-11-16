package ui;

import javax.swing.*;
import java.awt.*;

public class CreateGUI extends JPanel {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private MainGraphicUIApp mainGUI;
    private JTextField quizNameField;
    private JTextField questionField;
    private JTextField answerField;
    private JTextField falseOne;
    private JTextField falseTwo;
    private JTextField falseThree;

    public CreateGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addQuizLabel();
        addEmptySpace();
        addQuestionLabel();
        addEmptySpace();
        addCorrectAnswerLabel();
        addEmptySpace();
        addWrongAnswersLabels();

        setVisible(true);
    }

    public void addQuizLabel() {
        JLabel quizNameText = new JLabel("Enter the Quiz Name:");
        quizNameText.setBounds(10, 20, 80, 25);
        add(quizNameText);

        quizNameField = new JTextField();
        quizNameField.setBounds(100, 20, 165, 25);
        add(quizNameField);
    }

    public void addQuestionLabel() {
        JLabel questionText = new JLabel("Enter the Question:");
        questionText.setBounds(10, 50, 80, 25);
        add(questionText);

        questionField = new JTextField();
        questionField.setBounds(100, 50, 165, 25);
        add(questionField);
    }

    public void addCorrectAnswerLabel() {
        JLabel answerText = new JLabel("Enter the Answer:");
        answerText.setBounds(10, 80, 80, 25);
        add(answerText);

        answerField = new JTextField();
        answerField.setBounds(100, 80, 165, 25);
        add(answerField);
    }

    public void addWrongAnswersLabels() {
        JLabel falseAnswerOne = new JLabel("Enter One Wrong Answer:");
        falseAnswerOne.setBounds(10, 110, 80, 25);
        add(falseAnswerOne);

        falseOne = new JTextField();
        falseOne.setBounds(100, 110, 165, 25);
        add(falseOne);

        JLabel falseAnswerTwo = new JLabel("Enter Another Wrong Answer:");
        falseAnswerTwo.setBounds(10, 140, 80, 25);
        add(falseAnswerTwo);

        falseTwo = new JTextField();
        falseTwo.setBounds(100, 140, 165, 25);
        add(falseTwo);

        JLabel falseAnswerThree = new JLabel("Enter Last Wrong Answer:");
        falseAnswerThree.setBounds(10, 170, 80, 25);
        add(falseAnswerThree);

        falseThree = new JTextField();
        falseThree.setBounds(100, 170, 165, 25);
        add(falseThree);
    }

    public void addEmptySpace() {
        add(Box.createRigidArea(new Dimension(0, 20)));
    }




}
