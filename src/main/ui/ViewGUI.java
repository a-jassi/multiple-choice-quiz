package ui;

import model.Question;
import model.Quiz;
import model.QuizManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private JTextField inputQuizName;
    private JButton viewButton;
    private JLabel quizName;

    public ViewGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpLabels();
        setUpTextField();
        setUpEnterButton();
    }

    private void setUpLabels() {
        List<Quiz> quizzes = mainGUI.getQuizManager().getAllQuizzesMade();
        for (Quiz quiz : quizzes) {
            JLabel label = new JLabel(quiz.getName());
            add(label);
        }

        JLabel text = new JLabel("<html><br><br>Input the quiz to view:</html>");
        add(text);
    }

    private void setUpTextField() {
        inputQuizName = new JTextField();
        inputQuizName.setColumns(20);
        add(inputQuizName);
    }

    private void setUpEnterButton() {
        viewButton = new JButton("Enter");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseQuiz();
            }
        });
        add(viewButton);
    }

    private void chooseQuiz() {
        if (!inputQuizName.getText().equals("")) {
            QuizManager quizManager = mainGUI.getQuizManager();
            Quiz quiz = quizManager.getQuizFromName(inputQuizName.getText());
            viewQuiz(quiz);
            setVisible(false);
        }
    }

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
