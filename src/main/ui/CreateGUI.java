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

    private MainGraphicUIApp mainGUI;
    private JTextField quizNameField;
    private JTextField questionField;
    private JTextField answerField;
    private JTextField falseOne;
    private JTextField falseTwo;
    private JTextField falseThree;
    private JButton createQuizButton;
    private JButton doneButton;
    private JButton addQuestionButton;
    private Quiz createdQuiz;

    // EFFECTS: creates a new createGUI panel
    public CreateGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setUpPanel();
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

    // EFFECTS: adds label and text field for quiz name
    public void addQuizLabel() {
        JLabel quizNameText = new JLabel("Enter the Quiz Name:");
        quizNameText.setBounds(10, 20, 80, 25);
        add(quizNameText);

        quizNameField = new JTextField();
        quizNameField.setBounds(100, 20, 165, 25);
        add(quizNameField);
    }

    // EFFECTS: adds the label and text field for the question
    public void addQuestionLabel() {
        JLabel questionText = new JLabel("Enter the Question:");
        questionText.setBounds(10, 50, 80, 25);
        add(questionText);

        questionField = new JTextField();
        questionField.setBounds(100, 50, 165, 25);
        add(questionField);
    }

    // EFFECTS: adds label and text field for correct answer
    public void addCorrectAnswerLabel() {
        JLabel answerText = new JLabel("Enter the Correct Answer:");
        answerText.setBounds(10, 80, 80, 25);
        add(answerText);

        answerField = new JTextField();
        answerField.setBounds(100, 80, 165, 25);
        add(answerField);
    }

    // EFFECTS: adds labels and text fields for wrong answers
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

    // EFFECTS: adds empty space to createGUI panel
    public void addEmptySpace() {
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    // MODIFIES: this
    // EFFECTS: sets the visibility for this to bool
    public void setCreateVisibility(Boolean bool) {
        setVisible(bool);
    }

    // EFFECTS: creates a create quiz button and adds it to the panel
    public void addCreateQuizButton() {
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

    public void addCreateQuestionButton() {
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
    public void addQuestionToQuiz(Quiz quiz) {
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
    public boolean hasEmptyString() {
        return quizNameField.getText().equals("")
                || questionField.getText().equals("")
                || answerField.getText().equals("")
                || falseOne.getText().equals("")
                || falseTwo.getText().equals("")
                || falseThree.getText().equals("");
    }

    // EFFECTS: creates a done button and adds it to the panel
    public void addDoneButton() {
        doneButton = new JButton("Done");
        doneButton.setBounds(50, 210, 80, 25);
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.getQuizManager().addToAllQuizzesMade(createdQuiz);
                setCreateVisibility(false);
            }
        });
        add(doneButton);
    }

    // the code from the clearTextFields method references code from the link below:
    // https://stackoverflow.com/questions/24473427/clear-textfield-using-button-java

    // EFFECTS: clears the text fields to empty fields
    public void clearTextFields() {
        questionField.setText("");
        answerField.setText("");
        falseOne.setText("");
        falseTwo.setText("");
        falseThree.setText("");
    }

}
