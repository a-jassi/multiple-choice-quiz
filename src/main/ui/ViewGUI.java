package ui;

import model.Question;
import model.Quiz;
import model.QuizManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// represents GUI for viewing a created quiz
public class ViewGUI extends JPanel {

    private MainGraphicUIApp mainGUI;           // main JFrame that displays panels
    private JTextField inputQuizName;           // text field for inputting quiz you want to view
    private JButton viewButton;                 // button to confirm quiz you want to view
    private JLabel quizName;                    // label that displays quiz name

    // EFFECTS: creates a viewGUI panel that has functionality related to viewing made quizzes
    public ViewGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpLabels();
        setUpTextField();
        setUpEnterButton();
    }

    // MODIFIES: this
    // EFFECTS: creates labels for all made quizzes and adds them to panel
    private void setUpLabels() {
        JLabel info = new JLabel("Here is a list of all quizzes:");
        add(info);
        add(Box.createRigidArea(new Dimension(0, 50)));

        List<Quiz> quizzes = mainGUI.getQuizManager().getAllQuizzesMade();
        for (Quiz quiz : quizzes) {
            JLabel label = new JLabel(quiz.getName());
            add(label);
        }

        JLabel text = new JLabel("<html><br><br>Input the quiz to view:</html>");
        add(text);
    }

    // MODIFIES: this
    // EFFECTS: creates new text field for inputting the quiz to view
    private void setUpTextField() {
        inputQuizName = new JTextField();
        inputQuizName.setColumns(20);
        add(inputQuizName);
    }

    // MODIFIES: this
    // EFFECTS: creates enter button to confirm what quiz to go to and adds to panel
    private void setUpEnterButton() {
        viewButton = new JButton("Enter");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseQuiz();
            }
        });
        add(viewButton);
    }

    // EFFECTS: gets quiz that is to be viewed
    private void chooseQuiz() {
        if (!inputQuizName.getText().equals("")) {
            QuizManager quizManager = mainGUI.getQuizManager();
            Quiz quiz = quizManager.getQuizFromName(inputQuizName.getText());
            viewQuiz(quiz);
            setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new panel for viewing all the questions added to a quiz
    private void viewQuiz(Quiz quiz) {
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        quizName = new JLabel("<html>Currently Looking at: " + quiz.getName() + "<br><br></html>");
        viewPanel.add(quizName);

        for (Question question : quiz.getQuestions()) {
            JLabel questionLabel = new JLabel(question.toStringAsHtml());
            viewPanel.add(questionLabel);
        }

        JButton returnToWelcome = new JButton("Return To Welcome Page");
        returnToWelcome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });
        viewPanel.add(returnToWelcome);

        mainGUI.setCurrentPanel(viewPanel);

    }

}
