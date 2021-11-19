package ui;

import model.Question;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// represents the gui for the create section of the multiple choice quiz app
public class CreateGUI extends JPanel {

    private MainGraphicUIApp mainGUI;       // JFrame that displays all the panels
    private JTextField quizNameField;       // field for inputting quiz name
    private JTextField questionField;       // field for inputting question name
    private JTextField answerField;         // field for inputting correct answer to question
    private JTextField falseOne;            // field for first false answer
    private JTextField falseTwo;            // field for second false answer
    private JTextField falseThree;          // field for third false answer
    private JButton createQuizButton;       // button that creates the quiz
    private JButton doneButton;             // button that stops quiz creation and returns you to menu
    private JButton addQuestionButton;      // button that adds question to quiz
    private Quiz createdQuiz;               // quiz created from createGUI

    // EFFECTS: creates a new createGUI panel for functionality related to creating a quiz
    public CreateGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setUpPanel();
        setVisible(true);
    }

    // EFFECTS: sets up createGUI panel with all buttons and text fields
    public void setUpPanel() {
        addQuizLabel();
        addEmptySpace();
        addQuestionLabel();
        addEmptySpace();
        addCorrectAnswerLabel();
        addEmptySpace();
        addWrongAnswersLabels();
        addEmptySpace();
        addCreateQuizButton();
        addEmptySpace();
        addDoneButton();
    }

    // MODIFIES: this
    // EFFECTS: adds label and text field for quiz name
    private void addQuizLabel() {
        JLabel quizNameText = new JLabel("Enter the Quiz Name:");
        quizNameText.setBounds(10, 20, 80, 25);
        add(quizNameText);

        quizNameField = new JTextField();
        quizNameField.setBounds(100, 20, 165, 25);
        add(quizNameField);
    }

    // MODIFIES: this
    // EFFECTS: adds the label and text field for the question
    private void addQuestionLabel() {
        JLabel questionText = new JLabel("Enter the Question:");
        questionText.setBounds(10, 50, 80, 25);
        add(questionText);

        questionField = new JTextField();
        questionField.setBounds(100, 50, 165, 25);
        add(questionField);
    }

    // MODIFIES: this
    // EFFECTS: adds label and text field for correct answer
    private void addCorrectAnswerLabel() {
        JLabel answerText = new JLabel("Enter the Correct Answer:");
        answerText.setBounds(10, 80, 80, 25);
        add(answerText);

        answerField = new JTextField();
        answerField.setBounds(100, 80, 165, 25);
        add(answerField);
    }

    // MODIFIES: this
    // EFFECTS: adds labels and text fields for wrong answers
    private void addWrongAnswersLabels() {
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

    // EFFECTS: adds empty space to createGUI panel acting as a divider
    private void addEmptySpace() {
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    // MODIFIES: this
    // EFFECTS: sets the visibility for this to bool
    private void setCreateVisibility(Boolean bool) {
        setVisible(bool);
    }

    // MODIFIES: this
    // EFFECTS: creates a create quiz button and adds it to the panel
    private void addCreateQuizButton() {
        createQuizButton = new JButton("Create Quiz");
        createQuizButton.setBounds(50, 210, 80, 25);
        createQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!hasEmptyString()) {
                    quizNameField.setEditable(false);
                    String quizName = quizNameField.getText();
                    createdQuiz = new Quiz(quizName);
                    addQuestionToQuiz(createdQuiz);
                    clearTextFields();
                    remove(createQuizButton);
                    addCreateQuestionButton();
                    repaint();
                }
            }
        });
        add(createQuizButton);
    }

    // MODIFIES: this
    // EFFECTS: creates an add question button and adds it to the createGUI panel
    private void addCreateQuestionButton() {
        addQuestionButton = new JButton("Add Question");
        addQuestionButton.setBounds(25, 330, 120, 25);
        addQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!hasEmptyString()) {
                    addQuestionToQuiz(createdQuiz);
                    clearTextFields();
                }
            }
        });
        add(addQuestionButton);
    }

    // MODIFIES: quiz
    // EFFECTS: combines the text fields and creates a new question and adds it to quiz
    private void addQuestionToQuiz(Quiz quiz) {
        String questionName = questionField.getText();
        String correctAnswer = answerField.getText();
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);
        answers.add(falseOne.getText());
        answers.add(falseTwo.getText());
        answers.add(falseThree.getText());
        Question createdQuestion = new Question(questionName, correctAnswer, answers);
        quiz.addQuestion(createdQuestion);
    }

    // EFFECTS: returns true if any of the text fields are empty, false otherwise
    private boolean hasEmptyString() {
        return quizNameField.getText().equals("")
                || questionField.getText().equals("")
                || answerField.getText().equals("")
                || falseOne.getText().equals("")
                || falseTwo.getText().equals("")
                || falseThree.getText().equals("");
    }

    // MODIFIES: this
    // EFFECTS: creates a done button and adds it to the panel
    private void addDoneButton() {
        doneButton = new JButton("Done");
        doneButton.setBounds(50, 210, 80, 25);
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.getQuizManager().addToAllQuizzesMade(createdQuiz);
                mainGUI.resetToWelcome();
            }
        });
        add(doneButton);
    }

    // the code from the clearTextFields method references code from the link below:
    // https://stackoverflow.com/questions/24473427/clear-textfield-using-button-java

    // MODIFIES: this
    // EFFECTS: clears the text fields to empty fields
    private void clearTextFields() {
        questionField.setText("");
        answerField.setText("");
        falseOne.setText("");
        falseTwo.setText("");
        falseThree.setText("");
    }
}
